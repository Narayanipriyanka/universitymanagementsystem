package com.example.feemanagementservice.service;

import com.example.events.ExamFeePaidEvent;
import com.example.events.FeeInvoiceEvent;
import com.example.events.PayFeesEvent;
import com.example.events.PaymentLinkEvent;
import com.example.feemanagementservice.dto.FeeStructureDTO;
import com.example.feemanagementservice.dto.PaymentDTO;
import com.example.feemanagementservice.entity.*;
import com.example.feemanagementservice.repository.EnrolledCoursesRepository;
import com.example.feemanagementservice.repository.FeeRecordRepository;
import com.example.feemanagementservice.repository.FeeStructureRepository;
import com.example.feemanagementservice.repository.PaymentRepository;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FeesService {
    private final RazorpayClient razorpayClient;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private EnrolledCoursesRepository enrolledCoursesRepository;
    @Autowired
    private FeeStructureRepository feeStructureRepository;
    @Autowired
    private FeeRecordRepository feeRecordRepository;
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public FeesService(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }
    public String addFeeStructure(FeeStructureDTO dto){
        FeeStructure f=new FeeStructure();
        f.setExamFee(dto.getExamFee());
        f.setRecordsFee(dto.getRecordsFee());
        f.setDeptCode(dto.getDeptCode());
        f.setTutionFee(dto.getTutionFee());
        f.setSemester(dto.getSemester());
        f.setProgramCode(dto.getProgramCode());
        feeStructureRepository.save(f);
        return "fee structure added successfully";
    }
    public String getFeeStructureOfProgram(String programCode,String deptCode){
        List<FeeStructure> f=feeStructureRepository.findAllByProgramCode(programCode);
        StringBuilder struct= new StringBuilder();
        for(FeeStructure fees:f){
            if(Objects.equals(fees.getDeptCode(), deptCode)){
                struct.append(fees.getSemester()).append(" st semester Tution fee is").append(fees.getTutionFee()).append(" Exam fees is").append(fees.getExamFee()).append(" Records FEE is").append(fees.getRecordsFee());
            }
        }
        return struct.toString();
    }
public String feePaymentTracking(UUID studentID,PaymentFor category){
        List<Payment> p=paymentRepository.findAllByStudentId(studentID);
        for(Payment payment:p){
            if(payment.getCategory()==category){
                return payment.getStatus().toString();
            }
        }
        return " ";
}
    public String payFees(PaymentDTO payDTO) throws RazorpayException {
        FeeRecord f=feeRecordRepository.findByStudentId(payDTO.getStudentId()).orElseThrow(()->new RuntimeException("no student fee record found"));
        f.setTotalbalance(f.getTotalbalance()-payDTO.getAmount());
        f.setPaid(f.getPaid()+payDTO.getAmount());
        EnrolledCourses e=enrolledCoursesRepository.findByStudentIdAndSemesterAndProgram(payDTO.getStudentId(),payDTO.getSemester(),payDTO.getProgram());
        Double categoryBalance=0.0;
        if(payDTO.getCategory()== PaymentFor.EXAM_FEE){
            ExamFeePaidEvent event=new ExamFeePaidEvent(payDTO.getStudentId(),payDTO.getProgram(),payDTO.getSemester(),e.getCourseCode());
            kafkaTemplate.send("examFeePaid",event);
            f.setExamFeeBalance(f.getExamFeeBalance()-payDTO.getAmount());
            categoryBalance+=f.getExamFeeBalance()-payDTO.getAmount();
        }
        else if(payDTO.getCategory()== PaymentFor.HOSTEL_FEE){
            f.setHostelFeeBalance(f.getHostelFeeBalance()-payDTO.getAmount());
            categoryBalance+=f.getHostelFeeBalance()-payDTO.getAmount();

        }
        else if(payDTO.getCategory()== PaymentFor.TUTION_FEE){
            f.setTutionFeeBalance(f.getTutionFeeBalance()-payDTO.getAmount());
            categoryBalance+=f.getTutionFeeBalance()-payDTO.getAmount();

        }
        else if(payDTO.getCategory()== PaymentFor.MESS_FEE){
            f.setMessFeeBalance(f.getMessFeeBalance()-payDTO.getAmount());
            categoryBalance+=f.getMessFeeBalance()-payDTO.getAmount();

        }
        else if(payDTO.getCategory()== PaymentFor.RECORD_FEE){
            f.setRecordFeeBalance(f.getRecordFeeBalance()-payDTO.getAmount());
            categoryBalance+=f.getRecordFeeBalance()-payDTO.getAmount();

        }
        feeRecordRepository.save(f);

        FeeInvoiceEvent event=new FeeInvoiceEvent(payDTO.getStudentId(),payDTO.getCategory().toString(),payDTO.getAmount(),categoryBalance,f.getTotalbalance(), LocalDate.now());
        kafkaTemplate.send("sendInvoice",event);
        List<Payment> pays=paymentRepository.findAllByStudentId(payDTO.getStudentId());
        for(Payment p:pays){
           if(p.getCategory()==payDTO.getCategory()&&p.getAmount()==payDTO.getAmount())
           {p.setPaymentMethod(payDTO.getPaymentMethod());
               p.setStatus(PaymentStatus.PROCESSING);
               if(payDTO.getPaymentMethod()==PaymentMethod.ONLINE_BANKING||payDTO.getPaymentMethod()==PaymentMethod.UPI){
                   JSONObject paymentLink =new JSONObject();
                   paymentLink.put("amount",p.getAmount()*100);
                   paymentLink.put("currency","INR");
                   paymentLink.put("accept_partial",false);
                   paymentLink.put("description",p.getCategory());
                   JSONObject customer=new JSONObject();
                   JSONObject notify=new JSONObject();
                   notify.put("email",true);
                   paymentLink.put("notify",notify);
                   PaymentLink payment=razorpayClient.paymentLink.create(paymentLink);
                   String payLink=payment.get("short_url");
                   paymentRepository.save(p);
                   PaymentLinkEvent dto=new PaymentLinkEvent(p.getStudentId(),p.getAmount(),payLink);
                   kafkaTemplate.send("sendPaymentLink",dto);
                   return "fees payment link is send to your email kindly pay using that link";

               }
               else{
                   return "pay cash at the university officeroom";
               }
           }
           else{
               return "your payment category and amount does not matched with our records";
           }
       }

       return " ";
    }
    }


package com.example.examservice.service;

import com.example.events.PayFeesEvent;
import com.example.examservice.dto.ExamDTO;
import com.example.examservice.dto.ReevaluationDTO;
import com.example.examservice.entity.Exam;
import com.example.examservice.entity.ExamFeeDetails;
import com.example.examservice.entity.ExamRegister;
import com.example.examservice.entity.RevaluationRequest;
import com.example.examservice.repository.ExamFeeDetailsRepository;
import com.example.examservice.repository.ExamRegisterRepository;
import com.example.examservice.repository.ExamRepository;
import com.example.examservice.repository.RevaluationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ExamFeeDetailsRepository examFeeDetailsRepository;
    @Autowired
    private ExamRegisterRepository examRegisterRepository;
    @Autowired
    private RevaluationRequestRepository revaluationRequestRepository;
    @Autowired
    private KafkaTemplate<String ,Object> kafkaTemplate;

    public String addExam(ExamDTO dto) {
        Exam e = new Exam();
        e.setProgramCode(dto.getProgramCode());
        e.setCourseCode(dto.getCourseCode());
        e.setExam(dto.getExam());
        e.setExamDate(dto.getExamDate());
        e.setTotalMarks(dto.getTotalMarks());
        e.setEndTime(dto.getEndTime());
        e.setStartTime(dto.getStartTime());
        e.setDeptCode(dto.getDeptCode());
        e.setSemester(dto.getSemester());
        examRepository.save(e);
        return "exam added successfully";
    }
    public String getExamSchedule(String examName,String programCode, Integer semester, String deptCode) {
        List<Exam> exams = examRepository.findAllByProgramCodeAndDeptCode(programCode, deptCode);
        StringBuilder schedule = new StringBuilder(" ");
        for (Exam exam : exams) {
            if (Objects.equals(exam.getSemester(), semester)&& Objects.equals(exam.getExam(), examName)) {
                schedule.append("Subject:").append(exam.getCourseCode()).append(" examDate:").append(exam.getExamDate()).append(" time:").append(exam.getStartTime()).append("to ").append(exam.getEndTime()).append("\n");
            }

        }
        return schedule.toString();
    }

    public String registerForExam(UUID studentID, String examName, String programCode, Integer semster, String deptCode){
        List<Exam> e=examRepository.findByExam(examName);
        Boolean feePaid=false;
        List<ExamFeeDetails> examFeeDetails=examFeeDetailsRepository.findBySemesterAndProgramCode(semster,programCode);
        for(ExamFeeDetails ex:examFeeDetails) {
            if (ex.getStudentId() == studentID && ex.getFeePaid()) {
                feePaid = true;
                break;
            }
        }
            for(Exam exam:e){
                if(Objects.equals(exam.getDeptCode(), deptCode) && Objects.equals(exam.getProgramCode(), programCode) && Objects.equals(exam.getSemester(), semster)){
                    if(feePaid){
                       ExamRegister examRegister=examRegisterRepository.findByExamName(exam.getExam());
                        examRegister.setExamId(exam.getId());
                        List<UUID> studentIds=examRegister.getStudentIds();
                        studentIds.add(studentID);
                        examRegister.setStudentIds(studentIds);
                        examRegisterRepository.save(examRegister);
                       return  "registered for "+exam.getExam()+" successfully";
                }
            }
        }
            return " exam fee is not paid pay it in exam section ";
    }
    public String addRevaluationForExam(ReevaluationDTO dto){
        RevaluationRequest request=new RevaluationRequest();
        request.setExamName(dto.getExamName());
        request.setDeptCode(dto.getDeptCode());
        request.setSemester(dto.getSemester());
        request.setExamId(dto.getExamId());
        request.setProgramCode(dto.getProgramCode());
        request.setFee(dto.getFee());
        revaluationRequestRepository.save(request);
        return "revalution details added successfully for exam"+dto.getExamName();
    }
    public String applyForRevaluation(UUID studentId,Integer semester,String examName,String programName){
        RevaluationRequest r=revaluationRequestRepository.findByExamName(examName);
        if(Objects.equals(r.getProgramCode(), programName) && Objects.equals(r.getSemester(), semester)) {
           List<UUID> studentIds=r.getStudentId();
           studentIds.add(studentId);
           r.setStudentId(studentIds);
           revaluationRequestRepository.save(r);
            PayFeesEvent event=new PayFeesEvent(studentId,r.getFee(),"REEVALUATION");
            kafkaTemplate.send("payFees",event);
        }
        return " pay revaluation fee Rs"+r.getFee()+" at the fee module to apply";
    }

}

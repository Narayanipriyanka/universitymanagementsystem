package com.example.studentservice.service;

import com.example.events.SendScholarshipEvent;
import com.example.studentservice.dto.ScholarshipStatusDTO;
import com.example.studentservice.entity.Scholarship;
import com.example.studentservice.dto.ScholarshipDTO;
import com.example.studentservice.entity.ScholarshipStatus;
import com.example.studentservice.entity.Student;
import com.example.studentservice.repository.ScholarshipRepository;
import com.example.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.studentservice.entity.ScholarshipStatus.APPLIED;

@Service
public class ScholarshipService {
    @Autowired
    private ScholarshipRepository scholarshipRepository;
    @Autowired
    private StudentRepository studentRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    public ScholarshipService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public String applyForScholarship(ScholarshipDTO scholarshipDTO){
        Student s=studentRepository.findById(scholarshipDTO.getStudentId()).orElseThrow(()-> new RuntimeException("no student found with this id"));
        Scholarship scholarship=scholarshipRepository.findByScholarshipName(scholarshipDTO.getScholarshipName());
        if(scholarship.getStudent().getId()==scholarshipDTO.getStudentId()){
           throw new RuntimeException("already appiled for this scholarship ");
        }
        Scholarship sc=new Scholarship();
        sc.setAmount(scholarshipDTO.getAmount());
        sc.setScholarshipName(scholarshipDTO.getScholarshipName());
        sc.setStatus(APPLIED);
       sc.setStudentID(scholarshipDTO.getStudentId());
       sc.setStudent(s);
        scholarshipRepository.save(sc);
        return "Scholarship applied successfully  and under review preserve your scholarship id "+sc.getId()+"for further tracking of your scholarship status";
    }
    public String updateStatus(ScholarshipStatusDTO statusDTO){
        Student s=studentRepository.findById(statusDTO.getStudentId()).orElseThrow(()->new RuntimeException("no student found"));
        Scholarship sc=new Scholarship();
        sc.setAmount(statusDTO.getAmount());
        sc.setScholarshipName(statusDTO.getScholarshipName());
        sc.setStudentID(statusDTO.getStudentId());
        sc.setStudent(s);
        if(statusDTO.getStatus()==ScholarshipStatus.APPROVED){
            sc.setStatus(ScholarshipStatus.PAYMENT_PROCESSING);
            scholarshipRepository.save(sc);
            SendScholarshipEvent dto=new SendScholarshipEvent(s.getEmail(),"PAYMENT_PROCESSING");
            kafkaTemplate.send("sendScholarship",dto);
            return "status for student "+s.getFirstName()+"with id no "+statusDTO.getStudentId()+" is updated to "+statusDTO.getStatus();
        }
        else if(statusDTO.getStatus()== ScholarshipStatus.CREDITED){
            sc.setStatus(ScholarshipStatus.CREDITED);
            scholarshipRepository.save(sc);
            SendScholarshipEvent dto=new SendScholarshipEvent(s.getEmail(),"PAYMENT_PROCESSING");
            kafkaTemplate.send("sendScholarship",dto);
            return "status for student "+s.getFirstName()+"with id no "+statusDTO.getStudentId()+" is updated to "+statusDTO.getStatus();
        }
        else if(statusDTO.getStatus()== ScholarshipStatus.REJECTED){
            sc.setStatus(ScholarshipStatus.REJECTED);
            scholarshipRepository.save(sc);
            SendScholarshipEvent dto=new SendScholarshipEvent(s.getEmail(),"PAYMENT_PROCESSING");
            kafkaTemplate.send("sendScholarship",dto);

            return "status for student "+s.getFirstName()+"with id no "+statusDTO.getStudentId()+" is updated to "+statusDTO.getStatus();
        }
        else {
            return "status updated successfully";

        }
    }
    public List<Scholarship> getStudentScholarships(UUID studentId) {
        return scholarshipRepository.findByStudentId(studentId);
    }
    public Scholarship trackScholarship(Long id){
        return scholarshipRepository.findById(id).orElseThrow(()-> new RuntimeException("no scholarship found with this id"));
    }

}

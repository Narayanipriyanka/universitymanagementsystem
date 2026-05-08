package com.example.studentservice.service;

import com.example.events.AcademicsEvent;
import com.example.studentservice.dto.AcademicRecordDTO;
import com.example.studentservice.dto.TranscriptDTO;
import com.example.studentservice.entity.AcademicsRecord;
import com.example.studentservice.entity.Student;
import com.example.studentservice.repository.AcademicsRepository;
import com.example.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AcademicService {
    @Autowired
    private AcademicsRepository academicsRepository;
    @Autowired
    private StudentRepository studentRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public AcademicService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public AcademicsRecord addRecord(AcademicRecordDTO academicRecordDTO){
        AcademicsRecord record=new AcademicsRecord();
        record.setStudentId(academicRecordDTO.getStudentId());
        record.setSubject(academicRecordDTO.getSubject());
        record.setSemester(academicRecordDTO.getSemester());
        record.setMarks(academicRecordDTO.getMarks());
        record.setGrade(calculateGrade(academicRecordDTO.getMarks()));
        AcademicsEvent dto=new AcademicsEvent(record.getStudentId(),record.getSubject(),record.getMarks(),record.getSemester(),record.getGrade());
        kafkaTemplate.send("sendAcademics",dto);
        return academicsRepository.save(record);
    }
    public List<AcademicsRecord> getAllAcademicRecordsOfStudent(UUID studentId){
        return academicsRepository.findByStudentId(studentId);
    }
    public TranscriptDTO getTranscriptReport(UUID studentId){
        Student student=studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("student not found"));
        List<AcademicsRecord> records=academicsRepository.findByStudentId(studentId);
        double cgpa=calculateCGPA(records);
        return new TranscriptDTO(studentId,student.getFirstName(),records,cgpa);
    }
    private String calculateGrade(Double marks){
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B";
        if (marks >= 60) return "C";
        return "F";
    }
    private double calculateCGPA(List<AcademicsRecord> records){
        double total=0.0;
        for(AcademicsRecord r:records){
            total+=covertMarksTopoint(r.getMarks());
        }
      return total/records.size();
    }

    private double covertMarksTopoint(Double marks) {
        if (marks >= 90) return 10;
        if (marks >= 80) return 9;
        if (marks >= 70) return 8;
        if (marks >= 60) return 7;
        return 0; }


}

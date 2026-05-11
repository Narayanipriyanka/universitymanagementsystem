package com.example.gradeservice.service;



import com.example.events.AcademicsEvent;

import com.example.events.StudentCreatedEvent;
import com.example.gradeservice.dto.AcademicRecordDTO;
import com.example.gradeservice.dto.TranscriptDTO;
import com.example.gradeservice.entity.AcademicsRecord;
import com.example.gradeservice.repository.AcademicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AcademicService {
    @Autowired
    private AcademicsRepository academicsRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public AcademicService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public List<AcademicsRecord> addResultsCsv(MultipartFile file) throws IOException {
        List<AcademicsRecord> academicsRecords=new ArrayList<>();
        BufferedReader reader=new BufferedReader(new InputStreamReader(file.getInputStream()));
        reader.readLine();
        String line;
        while((line=reader.readLine())!=null){
            String[] data=line.split(",");
            AcademicsRecord s=new AcademicsRecord();
            s.setStudentId(UUID.fromString(data[0]));
            s.setSubject(data[1]);
            s.setSemester(Integer.parseInt(data[2]));
            s.setMidMarks(Double.parseDouble(data[3]));
            s.setQuizMarks(Double.parseDouble(data[4]));
            s.setLabMarks(Double.parseDouble(data[5]));
            s.setFinalExamMarks(Double.parseDouble(data[6]));
            s.setTotalMarks(Double.parseDouble(data[3])+Double.parseDouble(data[4])+Double.parseDouble(data[5]));
            s.setGrade(calculateGrade(Double.parseDouble(data[3])+Double.parseDouble(data[4])+Double.parseDouble(data[5])));
            s.setPass(!calculateGrade(Double.parseDouble(data[3])+Double.parseDouble(data[4])+Double.parseDouble(data[5])).equals("F"));
            academicsRecords.add(s);
            AcademicsEvent dto=new AcademicsEvent(UUID.fromString(data[0]),data[1],s.getTotalMarks(),s.getSemester(), s.getGrade(),s.getPass());
            kafkaTemplate.send("sendAcademics",dto);
        }
        return academicsRepository.saveAll(academicsRecords);
    }
    public AcademicsRecord addRecord(AcademicRecordDTO academicRecordDTO){
        AcademicsRecord record=new AcademicsRecord();
        record.setStudentId(academicRecordDTO.getStudentId());
        record.setSubject(academicRecordDTO.getSubject());
        record.setSemester(academicRecordDTO.getSemester());
        record.setTotalMarks(academicRecordDTO.getMidMarks()+ academicRecordDTO.getFinalExamMarks()+ academicRecordDTO.getQuizMarks());
        record.setLabMarks(academicRecordDTO.getLabMarks());
        record.setMidMarks(academicRecordDTO.getMidMarks());
        record.setQuizMarks(academicRecordDTO.getQuizMarks());
        record.setGrade(calculateGrade(academicRecordDTO.getLabMarks()+academicRecordDTO.getFinalExamMarks()+academicRecordDTO.getQuizMarks()));
        record.setPass(!calculateGrade(academicRecordDTO.getLabMarks() + academicRecordDTO.getFinalExamMarks() + academicRecordDTO.getQuizMarks()).equals("F"));
        AcademicsEvent dto=new AcademicsEvent(record.getStudentId(),record.getSubject(),record.getMarks(),record.getSemester(),record.getGrade(),record.getPass());
        kafkaTemplate.send("sendAcademics",dto);
        return academicsRepository.save(record);
    }
    public List<AcademicsRecord> getAllAcademicRecordsOfStudent(UUID studentId){
        return academicsRepository.findByStudentId(studentId);
    }
    public String getResultInSemester(UUID studentId,Integer semester){
        List<AcademicsRecord> records=getAllAcademicRecordsOfStudent(studentId);
        StringBuilder result= new StringBuilder();
        for(AcademicsRecord r:records){
            if(Objects.equals(r.getSemester(), semester)){
                result.append("Subject:").append(r.getSubject()).append(" marks:").append(r.getTotalMarks()).append("status:").append(r.getPass()?"PASS":"FAIL").append("\n");
            }
        }
        return result.toString();
    }
    public TranscriptDTO getTranscriptReport(UUID studentId){
        List<AcademicsRecord> records=academicsRepository.findByStudentId(studentId);
        double cgpa=calculateCGPA(records);
        return new TranscriptDTO(studentId,records,cgpa);
    }
    public String passPercentage(Integer semester){
        List<AcademicsRecord> records=academicsRepository.findAllBySemester(semester);
        Integer pass=0;
        Integer fail=0;
        for(AcademicsRecord a:records){
            if(a.getPass()){
                pass++;
            }
            else{
                fail++;
            }
        }
        return "overall pass percentage is "+pass/records.size()*100+"%\n overall failure percentage is "+fail/records.size()*100+"%";
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

package com.example.studentservice.service;

import com.example.events.IdCardEvent;
import com.example.studentservice.entity.Student;
import com.example.studentservice.entity.StudentIDcardStatus;
import com.example.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class IDCardGenerationService {
    @Autowired
    private StudentRepository studentRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    public IDCardGenerationService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public String applyForIDCard(UUID studentID,String fullname,String fatherName,String email,String gender,String address,String bloodGroup,String phone, MultipartFile photo) throws IOException {
        String baseDir = "C:/university-management-system/uploads/";
        File dir = new File(baseDir + "photos/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
        String photoPath = baseDir + "photos/" + fileName;
        photo.transferTo(new File(photoPath));
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new RuntimeException("student not found"));
        student.setEmail(email);
        student.setAddress(address);
        student.setBloodGroup(bloodGroup);
        student.setPhoneNum(phone);
        student.setPhotoPath(photoPath);
        student.setStatus(StudentIDcardStatus.PENDING);
        studentRepository.save(student);
        return "Successfully applied for ID card - Status: " + StudentIDcardStatus.PENDING;
    }

    public String generateIdCard(UUID studentId) throws Exception {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        if (student.getStatus()==StudentIDcardStatus.INVALID) {
            throw new RuntimeException("Student is invalid");
        }else if(student.getStatus()==StudentIDcardStatus.PENDING) {
            File dir = new File("C:/university-management-system/idcard/");
            if (!dir.exists()) dir.mkdirs();
            String filePath = "C:/university-management-system/idcard/IDCARD_" + student.getId() + ".html";
            String content =
                    "<html>" +
                            "<body style='font-family:San serif'>" +
                            "<h2>UNIVERSITY ID CARD</h2>" +
                            "<hr/>" +
                            "<img src='file:///" + student.getPhotoPath() + "' width='120' height='120'/>" +
                            "<p><b>ID:</b> " + student.getId() + "</p>" +
                            "<p><b>Name:</b> " + student.getFirstName() + "</p>" +
                            "<p><b>Email:</b> " + student.getEmail() + "</p>" +
                            "<p><b>gender:</b> " + student.getGender() + "</p>" +
                            "<p><b>Department:</b> " + student.getDepartment() + "</p>" +
                            "<p><b>Blood Group:</b> " + student.getBloodGroup() + "</p>" +
                            "<p><b>Address:</b> " + student.getAddress() + "</p>" +
                            "<p><b>Phone:</b> " + student.getPhoneNum() + "</p>" +
                            "</body></html>";

            Files.write(Paths.get(filePath), content.getBytes());
            student.setIdCardPath(filePath);
            student.setStatus(StudentIDcardStatus.GENERATED);
            studentRepository.save(student);
            IdCardEvent dto=new IdCardEvent(student.getEmail(),filePath);
            kafkaTemplate.send("sendIdcard",dto);
            return "ID card generated successfully for " + student.getFirstName() + " " + student.getId();
        } else{
            return "invalid details";
        }}
    public ResponseEntity<List<Student>> getPendingStudents() {
        List<Student> pending = studentRepository.findByStatus(
                com.example.studentservice.entity.StudentIDcardStatus.PENDING
        );
        return ResponseEntity.ok(pending);
    }
}

package com.example.libraryservice.service;

import com.example.events.DigitalResourcesEvent;
import com.example.libraryservice.entity.DigitalResource;
import com.example.libraryservice.repository.DigitalResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DigitalResourceService {
    @Autowired
    private DigitalResourcesRepository digitalResourceRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public DigitalResourceService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String uploadPDF(String courseCode, MultipartFile file) throws IOException {
        String baseDir = "C:/university-management-system/uploads/";
        File dir = new File(baseDir + "PDF/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String resourcePath = baseDir + "PDF/" + fileName;
        file.transferTo(new File(resourcePath));
        List<DigitalResource> courses = digitalResourceRepository.findAllByCourseCode(courseCode);
        DigitalResource course=new DigitalResource();
       course.setFilePath(resourcePath);
       course.setCourseCode(courseCode);
       course.setFileName(file.getOriginalFilename());
       course.setFileType("PDF");
       courses.add(course);
       digitalResourceRepository.saveAll(courses);

        return "PDF uploaded Successfully for  " + courseCode;

    }
    public String uploadVideo(String courseCode, MultipartFile file) throws IOException {
        String baseDir = "C:/university-management-system/uploads/";
        File dir = new File(baseDir + "videos/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String resourcePath = baseDir + "videos/" + fileName;
        file.transferTo(new File(resourcePath));
        List<DigitalResource> courses = digitalResourceRepository.findAllByCourseCode(courseCode);
        DigitalResource course=new DigitalResource();
        course.setFilePath(resourcePath);
        course.setCourseCode(courseCode);
        course.setFileName(file.getOriginalFilename());
        course.setFileType("video");
        courses.add(course);
        digitalResourceRepository.saveAll(courses);

        return "video uploaded Successfully for  " + courseCode;

    }
  public String getResourcesOfCourse(String courseCode){
      List<DigitalResource> courses = digitalResourceRepository.findAllByCourseCode(courseCode);
      List<String> filepaths=new ArrayList<>();
      for(DigitalResource d:courses){
          filepaths.add(d.getFilePath());
      }
      DigitalResourcesEvent dto=new DigitalResourcesEvent(filepaths,courseCode,getUserName());
      kafkaTemplate.send("sendResources",dto);
      return "all the digital resources are send to your email";
  }
    private String getUserName(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt=(Jwt) authentication.getPrincipal();
        return jwt.getClaim("preferred_username");
    }
}

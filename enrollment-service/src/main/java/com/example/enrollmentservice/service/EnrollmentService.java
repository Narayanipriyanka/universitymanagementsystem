package com.example.enrollmentservice.service;

import com.example.enrollmentservice.dto.EnrollDTO;
import com.example.enrollmentservice.entity.Enrollment;
import com.example.enrollmentservice.entity.Section;
import com.example.enrollmentservice.entity.Student;
import com.example.enrollmentservice.repository.EnrollnmentRepository;
import com.example.enrollmentservice.repository.SectionRepository;
import com.example.enrollmentservice.repository.StudentRepository;
import com.example.events.CourseEnrollEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class EnrollmentService {
@Autowired
private EnrollnmentRepository enrollnmentRepository;
@Autowired
private StudentRepository studentRepository;
@Autowired
private SectionRepository sectionRepository;
private final KafkaTemplate<String,Object> kafkaTemplate;

    public EnrollmentService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String enrollInCourse(EnrollDTO dto){
    Enrollment e=new Enrollment();
    e.setCourseCode(dto.getCourseCode());
    e.setSem(dto.getSem());
    List<UUID> ids=e.getStudentid();
    ids.add(dto.getStudentId());
    e.setStudentid(ids);
    e.setDeadline(dto.getDeadLine());
    enrollnmentRepository.save(e);
        CourseEnrollEvent dtos=new CourseEnrollEvent(e.getCourseCode(),e.getSem(),e.getStudentid());
    kafkaTemplate.send("studentenrolls",dtos);
    return "enrolled successfully";
}
public String dropCourse(String courseCode){
        Student s=studentRepository.findByUsername(getUserName());
        List<Enrollment> enrollments=enrollnmentRepository.findAllByCourseCode(courseCode);
        for(Enrollment e:enrollments){
            if(e.getStudentids().contains(s.getId())&& LocalDate.now().isBefore(e.getDeadline())){
                List<UUID> studentIds=e.getStudentids();
                studentIds.remove(s.getId());
                e.setStudentids(studentIds);
                enrollnmentRepository.save(e);
            }
        }
        return "course dropped successfully";
}

public String checkSeatAvailabilityInCourse(String courseCode){
        List<Section> sections=sectionRepository.findAllByCourseCode(courseCode);
        Integer available=0;
        StringBuilder a= new StringBuilder(" ");
        for(Section s:sections){
            available+=s.getAvailableSeats();
            a.append(available).append("are available in ").append(s.getSectionId());
        }
        return a.toString();
}
    private String getUserName(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt=(Jwt) authentication.getPrincipal();
        return jwt.getClaim("preferred_username");
    }

    public String setStudentInSectionInCourse(String courseCode,UUID studentId){
        List<Enrollment> enrolls=enrollnmentRepository.findAllByCourseCode(courseCode);
        Student s=studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("no student found with this id"));
        for(Enrollment e:enrolls){
            for(UUID id:e.getStudentid()){
                if(id==studentId&&!s.getStudentStatus().equalsIgnoreCase("PROBATION")){
                    List<Section> sections=sectionRepository.findAllByCourseCode(courseCode);
                    for(Section section:sections){
                        if(section.getAvailableSeats()>0){
                            List<UUID> studentIds=section.getStudentIds();
                            studentIds.add(studentId);
                            section.setStudentIds(studentIds);
                            section.setAvailableSeats(section.getAvailableSeats()+1);
                            section.setAvailableSeats(section.getAvailableSeats()-1);
                            sectionRepository.save(section);
                        }

                    }
                }
            }
        }
     return "student is added to section";
    }

}

package com.example.facultyservice.service;


import com.example.events.CourseEvent;
import com.example.events.DepartmentEvent;
import com.example.events.RegisterRequest;
import com.example.facultyservice.entity.Course;
import com.example.facultyservice.entity.CourseAllocationStatus;
import com.example.facultyservice.entity.Department;
import com.example.facultyservice.repository.CourseRepository;
import com.example.facultyservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FacultyConsumer {
@Autowired
private DepartmentRepository departmentRepository;
@Autowired
private CourseRepository courseRepository;
    private final FacultyService facultyService;
    public FacultyConsumer(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @KafkaListener(topics="register",groupId="faculty-group")
    public void consume(RegisterRequest request){
        if(request == null){
            System.out.println("Request NULL");
            return;
        }  System.out.println("ROLE = " + request.getRole());
        System.out.println("Received: " + request.getFirstName());
        if("FACULTY".equalsIgnoreCase(request.getRole())){ System.out.println(" Saving Faculty...");
            facultyService.registerFaculty(request);
        }else
        {
            System.out.println(" Role error");
        }
    }
    @KafkaListener(topics="departments",groupId="faculty-department-group")
    public void consumeDepartment(DepartmentEvent request){
        Department d=new Department();
       d.setDegreecategory(request.getDegreeCategory());
       d.setDepartmentName(request.getDepartmentName());
       departmentRepository.save(d);
    }
    @KafkaListener(topics="courses",groupId="faculty-courses-group")
    public void consumeCourse(CourseEvent request){
        Course c=new Course();
        c.setSemester(request.getSemester());
        c.setCourseName(request.getCourseName());
        c.setProgram(request.getProgram());
        c.setStatus(CourseAllocationStatus.PENDING);
        courseRepository.save(c);
    }


}

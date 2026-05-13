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
    @KafkaListener(topics="register",groupId="faculty-group",containerFactory = "registerKafkaListenerContainerFactory")
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
    @KafkaListener(topics="departments",groupId="faculty-department-group",containerFactory = "departmentKafkaListenerContainerFactory")
    public void consumeDepartment(DepartmentEvent request){
        Department d=new Department();
        d.setHodId(request.getHodId());
        d.setHodName(request.getHodName());
        d.setDeptId(request.getDeptId());
       d.setDepartmentCode(request.getDepartmentCode());
       d.setDepartmentName(request.getDepartmentFullName());
       departmentRepository.save(d);
    }
    @KafkaListener(topics="courses",groupId="faculty-course-group",containerFactory = "courseKafkaListenerContainerFactory")
    public void consumeCourse(CourseEvent request){
        Course c=new Course();
        c.setSemester(request.getSemester());
        c.setCourseName(request.getCourseName());
        c.setCourseCode(request.getCode());
        c.setProgram(request.getProgram());
        c.setDeptCode(request.getDeptCode());
        c.setStatus(CourseAllocationStatus.PENDING);
        courseRepository.save(c);
    }


}

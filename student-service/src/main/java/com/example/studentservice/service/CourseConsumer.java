package com.example.studentservice.service;

import com.example.events.CourseEnrollEvent;
import com.example.events.CourseEvent;
import com.example.events.MaterialEvent;
import com.example.events.SyllabusEvent;
import com.example.studentservice.entity.Course;
import com.example.studentservice.entity.Student;
import com.example.studentservice.repository.CourseRepository;
import com.example.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseConsumer {
    @Autowired
    private CourseRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @KafkaListener(topics="studentenrolls",groupId="student-enroll-group")
    public void consume(CourseEnrollEvent event){
            List<Student> students=studentRepository.findAllByIdIn(event.getStudentIds());
            Course c=repository.findByCourseCode(event.getCourseCode());
            c.setStudents(students);
            c.setSemester(c.getSemester());
            repository.save(c);

    }
    @KafkaListener(topics="courses",groupId="student-courses-group")
    public void consume(CourseEvent event){
    Course c=new Course();
    c.setCourseCode(event.getCode());
    c.setProgram(event.getProgram());
    c.setSemester(c.getSemester());
    c.setDeptCode(event.getDeptCode());
    repository.save(c);
    }
    @KafkaListener(topics="sendSyllabus",groupId="student-syllabus-group")
    public void consume(SyllabusEvent event){
        Course c=repository.findByCourseCode(event.getCourseCode());
        c.setSyllabusPath(event.getFilePath());
        repository.save(c);

    }
    @KafkaListener(topics="sendMaterial",groupId="student-Material-group")
    public void consume(MaterialEvent event){
        Course c=repository.findByCourseCode(event.getCourseCode());
        List<String> paths=c.getMaterialPaths();
        paths.add(event.getFilePath());
        c.setMaterialPaths(paths);
        repository.save(c);

    }


}

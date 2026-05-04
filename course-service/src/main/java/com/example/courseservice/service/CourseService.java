package com.example.courseservice.service;

import com.example.courseservice.dto.CourseDTO;
import com.example.courseservice.dto.SectionDTO;
import com.example.courseservice.entity.Course;
import com.example.courseservice.entity.Sections;
import com.example.courseservice.repository.CourseRepository;
import com.example.courseservice.repository.SectionRepository;
import com.example.events.CourseEvent;
import com.example.events.MaterialEvent;
import com.example.events.SectionEvent;
import com.example.events.SyllabusEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SectionRepository sectionRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public CourseService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String addCourse(CourseDTO course){
        Course c=new Course();
        c.setCode(course.getCode());
        c.setCredits(course.getCredits());
        c.setDepartmentCode(course.getDepartmentCode());
        c.setName(course.getName());
        c.setProgramCode(course.getProgramCode());
        c.setSem(course.getSem());
     courseRepository.save(c);
        CourseEvent dto=new CourseEvent(c.getCode(),c.getDepartmentCode(),c.getCredits(),c.getName(),c.getSem(),c.getProgramCode());
     kafkaTemplate.send("courses",dto);
     return "course added successfully";
    }
    public List<Course> getCoursesOFSem(Integer sem){
        List<Course> courses=courseRepository.findBySem(sem);
        return courses;
    }
    public String uploadCourseSyllabus(String courseCode, MultipartFile file) throws IOException {
        String baseDir = "C:/university-management-system/uploads/";
        File dir = new File(baseDir + "syllabus/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String syllabusPath = baseDir + "syllabus/" + fileName;
        file.transferTo(new File(syllabusPath));
        Course course = courseRepository.findByCode(courseCode);
        course.setSyllabusPath(syllabusPath);
        courseRepository.save(course);
        SyllabusEvent dto=new SyllabusEvent(course.getSyllabusPath(), course.getCode());
        kafkaTemplate.send("sendSyllabus",dto);
        return "syllabus uploaded Successfully for  " + courseCode;

    }
    public String uploadCourseMaterial(String courseCode, MultipartFile file) throws IOException {
        String baseDir = "C:/university-management-system/uploads/";
        File dir = new File(baseDir + "materials/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String materialPath = baseDir + "materials/" + fileName;
        file.transferTo(new File(materialPath));
        Course course = courseRepository.findByCode(courseCode);
        course.setMaterialPath(materialPath);
        courseRepository.save(course);
        MaterialEvent dto=new MaterialEvent(course.getMaterialPath(),courseCode);
        kafkaTemplate.send("sendMaterial",dto);
        return "material uploaded Successfully for  " + courseCode;
    }

public String addSection(SectionDTO dto){
    Sections s=new Sections();
    s.setRoom(dto.getRoom());
    s.setCapacity(dto.getCapacity());
    s.setCourseCode(dto.getCourseCode());
    sectionRepository.save(s);
    SectionEvent dto=new SectionEvent(s.getSectionId(),s.getCapacity(),s.getRoom(),s.getCourseCode());
    kafkaTemplate.send("sendSections",dto);
    return "section added successfully";

}
public List<Sections> getSectionsByCourse(String code){
        List<Sections> s=sectionRepository.findAllByCourseCode(code);
        return s;
}

}

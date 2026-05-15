package com.example.courseservice.controller;

import com.example.courseservice.dto.CourseDTO;
import com.example.courseservice.dto.SectionDTO;
import com.example.courseservice.entity.Course;
import com.example.courseservice.entity.Sections;
import com.example.courseservice.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/course")
@Tag(name="University course controller")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can add a course here")
    public String addCourse(@RequestBody CourseDTO courseDTO){
        return courseService.addCourse(courseDTO);
    }
    @GetMapping("/sem")
    @Operation(summary = "get all courses in asem")
    public List<Course> getallCoursesInSem(@RequestParam Integer sem){
        return courseService.getCoursesOFSem(sem);
    }
    @PostMapping("/section")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can add a section to a course here")
    public String addSection(@RequestBody SectionDTO courseDTO){
        return courseService.addSection(courseDTO);
    }
    @GetMapping("/section")
    @Operation(summary = "get sections in a course here")
    public List<Sections> getallSectionsInCourse(@RequestParam String courseCode) {
        return courseService.getSectionsByCourse(courseCode);
    }
    @PostMapping(value = "/courseMaterial",consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can upload course materials here")
    public String uploadCourseMaterial(@RequestParam String courseCode, @RequestParam("file")MultipartFile file) throws IOException {
        return courseService.uploadCourseMaterial(courseCode,file);
    }
    @PostMapping(value = "/syllabus",consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can uplaod course syllabus here")
    public String addCourse(@RequestParam String courseCode,@RequestParam("file") MultipartFile file) throws IOException {
        return courseService.uploadCourseSyllabus(courseCode,file);
    }
}

package com.example.studentservice.controller;

import com.example.studentservice.dto.ParentDTO;
import com.example.studentservice.dto.ProfileDTO;
import com.example.studentservice.entity.Course;
import com.example.studentservice.entity.Parent;
import com.example.studentservice.entity.Student;
import com.example.studentservice.entity.StudentStatus;
import com.example.studentservice.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/student")
@SecurityRequirement(name = "keycloak")
@Tag(name="University student controller",description="for student services like student parent data ,Enrollment in courses per semester- connection with enrolmwnt servcie\n ")
public class StudentController {
    @Autowired
    private StudentService studentService;
   @GetMapping("/all")
   @PreAuthorize("hasRole('ADMIN')")
   @Operation(summary = "get students information", description = "for all students information this is only for admins")
    public List<Student> getStudents(){
       return studentService.getstudents();
   }
    @GetMapping("/idbyemail")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "get student id number ", description = "for all students information this is only for admins")
    public UUID getStudentId(@RequestParam String email){
        return studentService.getStudentId(email);
    }

   @PostMapping("/parent")
   @PreAuthorize("hasRole('STUDENT')")
   @Operation(summary = "add a parent", description = "student can add his or her parent here ")
    public String addParent(@RequestBody ParentDTO parent){
       return studentService.addParent(parent);
   }
    @PutMapping("/updateStatus")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "update student status", description = "only adminc an change the student status ")
    public String updatestatus(@RequestParam UUID stuentId, @RequestParam StudentStatus status){
        return studentService.updateStudentStatus(stuentId,status);
    }
    @PostMapping("/profile")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Admin creates student's profile", description = "for students profile creation like what semester ,which course,which year,etc.. ")
    public Student createProfile(@RequestBody ProfileDTO profile){
        return studentService.createProfile(profile);
    }
@GetMapping("/enroll")
@PreAuthorize("hasRole('STUDENT')")
@Operation(summary = "get list of enrolled courses", description = "student gets his or her courses enrolled list")
    public List<Course> getEnrollCourse(@RequestParam UUID studentId){
    return studentService.getEnrollCourses(studentId);

}
    @GetMapping("/syllabus")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "get syllabus of courses", description = "student gets his or her courses enrolled list")
    public String getSyllabusOfCourse(@RequestParam String code){
        return studentService.getSyllabusOfCourse(code);

    }
    @GetMapping("/mterial")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "get material of courses", description = "student gets his or her courses enrolled list")
    public String getMaterialOfCourse(@RequestParam String code){
        return studentService.getMaterialOfCourse(code);

    }

    @PostMapping(path = "/upload/csv", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get students information", description = "for all students information this is only for admins")
    public List<Student> uploadCSV(@RequestParam("file") MultipartFile file) throws Exception {
        return studentService.addStudentsCsv(file);

    }
    @PostMapping(path = "/upload/excel", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get students information", description = "for all students information this is only for admins")
    public List<Student> uploadStudentsExcel(@RequestParam("file") MultipartFile file) throws Exception {
        return studentService.uploadExcel(file);
    }
}

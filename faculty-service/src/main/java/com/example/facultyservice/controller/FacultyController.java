package com.example.facultyservice.controller;

import com.example.facultyservice.dto.FacultyProfileDTO;
import com.example.facultyservice.dto.PaySlipDTO;
import com.example.facultyservice.dto.QualificationDto;
import com.example.facultyservice.dto.ReviewDTO;
import com.example.facultyservice.entity.Department;
import com.example.facultyservice.entity.Faculty;
import com.example.facultyservice.entity.OfficeHours;
import com.example.facultyservice.entity.Reviews;
import com.example.facultyservice.service.FacultyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/faculty")
@Tag(name="student academics  controller",description="for student academic deatils like faculty will uplaod marks and students can get transcripts")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;
    @GetMapping("/{email}")
     @PreAuthorize("hasRole('FACULTY')")
    @Operation(summary = "get faculty id", description = "faculty can get their faculty id jsut by entering their email id here ")
    public UUID getFacultyId(@RequestParam String email){
        return facultyService.getFacultyID(email);
    }
    @GetMapping
    @Operation(summary = "get every faculty details of the university", description = "only for admin he can get information about alll faculties presentin the university")
    public List<Faculty> getAllFaculties(){
        return facultyService.getAllFaculties();
    }

    @PostMapping(path = "/uplaodcsv",consumes = "multipart/form-data")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "upload a list of profile of a faculty", description = "only for admin admin can uplaod csv file proile for a particular faculty here ")
    public List<Faculty> uploadFacultyCsv(@RequestParam(name = "file") MultipartFile file) throws IOException {
        return facultyService.addCsvOfFaculty(file);
    }
    @PostMapping("/qualification")
    @PreAuthorize("hasRole('FACULTY')")
    @Operation(summary = "faculty add qualification", description = "faculty can add his qualifications here ")
    public String addQualification(@RequestBody QualificationDto dto){
        return facultyService.addQualification(dto);
    }

    @PostMapping("/profile")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "create profile of a faculty", description = "only for admin admin can create proile for a particular faculty here ")
    public String createProfile(@RequestBody FacultyProfileDTO dto){
        System.out.println("DTO Faculty ID = " + dto.getFacultyID());
        return facultyService.createProfile(dto);
    }
    @PutMapping("/profile")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateProfile(@RequestBody FacultyProfileDTO dto){
        return facultyService.updateProfile(dto);
    }

    @PostMapping("/{facultyId}/{deptId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "add department to a faculty", description = "only admin can add department to a faculty here")
    public String addDepartment(@RequestParam UUID facultyId,@RequestParam List<String> names ){
        return facultyService.addDepartment(facultyId,names);
    }
    @GetMapping("/dept")
    @Operation(summary = "get all departments information", description = "anyone can get inforationa bout avalialable departments")
    public List<Department> getAllDepartments(){
        return facultyService.getAllDepartments();
    }
    @PostMapping("/officeHours")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "add office hours to a faculty", description = "only an admin can assign office hours toa  faculty here")
    public String addOfficeHours(@RequestBody OfficeHours hours){
        return facultyService.addOfficeHours(hours);
    }
    @GetMapping("/available/{facultyId}")
    @Operation(summary = "get available hours of a particular faculty", description = "anyone can get the respective facutly available time ")
    public String getAvailableHours(@RequestParam UUID facultyId){
        return facultyService.getAvailableHours(facultyId);
    }
    @GetMapping("/officeHours/{facultyId}")
    @Operation(summary = "get office hours of a faculty", description = "anyone cna get the office hours of a particular faculty here ")
    public OfficeHours getOfficeHours(@RequestParam UUID facultyId){
        return facultyService.getOfficeHoursOfAFaculty(facultyId);
    }

    @PostMapping("/review")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "add review to a faculty", description = "only a student can give review to a particular faculty here ")
    public String addReview(@RequestBody ReviewDTO dto){
        return facultyService.addReview(dto);
    }
    @GetMapping("/review/{facultyId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get reviews of a faculty", description = "only admin cna get the performance reviews of a particular faculty here ")
    public List<Reviews> getReviewsById(@RequestParam UUID facultyId){
        return facultyService.getReviewsById(facultyId);
    }
    @GetMapping("/overview/{facultyId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get a faculty's performance overview", description = "only admin cna get the performance overview of a faculty here ")
    public String getPerformanceOverview(@RequestParam UUID facultyId) {
        return facultyService.getPerformanceOverview(facultyId);
    }
    @PostMapping("/payroll")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "add payslip to a faculty", description = "only an admin can genertae payslips for a month to a faculty here")
    public String addPayslip(@RequestBody PaySlipDTO dto){
        return facultyService.addPaySlip(dto);
    }

}

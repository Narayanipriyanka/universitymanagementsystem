package com.example.examservice.controller;

import com.example.examservice.dto.ExamDTO;
import com.example.examservice.dto.ExamHallDTO;
import com.example.examservice.dto.ReevaluationDTO;
import com.example.examservice.dto.SeatDTO;
import com.example.examservice.service.ExamService;
import com.example.examservice.service.HallAllocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/exams")
@Tag(name = "Exam controller",description = " Handles seat allocation,apply for exam,reevalutaion ")
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private HallAllocationService hallAllocationService;
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "add  exam",description = "only admin can post   a exam")
    public String addExam(@RequestBody ExamDTO dto){
        return examService.addExam(dto);
    }
   @PostMapping("/revaluation")
   @PreAuthorize("hasRole('ADMIN')")
   @Operation(summary = "add revaluation for exam",description = "only admin can post revaluation of  a exam")
    public String addRevalutionForExam(@RequestBody ReevaluationDTO dto){
        return examService.addRevaluationForExam(dto);
    }
    @PutMapping("/revaluation")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "apply for revaluation for exam",description = "only student can apply for  revaluation of  a exam")
    public String addRevalutionForExam(@RequestParam UUID studentId, @RequestParam Integer semester, @RequestParam String examName,@RequestParam String programName){
        return examService.applyForRevaluation(studentId,semester,examName,programName);
    }
    @PostMapping("/register")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "register for exam",description = "only student can register a exam")
    public String registerForExam(@RequestParam UUID studentId, @RequestParam Integer semester,@RequestParam String examName, @RequestParam String deptCode,@RequestParam String programName){
        return examService.registerForExam(studentId,examName,programName,semester,deptCode);
    }
    @GetMapping("/schedule")
    @Operation(summary = "get exam schedule",description = "any one can get  exam schedule")
    public String registerForExam(@RequestParam Integer semester,@RequestParam String examName, @RequestParam String deptCode,@RequestParam String programName){
        return examService.getExamSchedule(examName,programName,semester,deptCode);
    }
    @PostMapping("/examhall")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "add exam hall ",description = "only admin can add examhalls ")
    public String addExamHall(@RequestBody ExamHallDTO dto){
        return hallAllocationService.addHall(dto);
    }
    @PostMapping("/seat")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "add seats to exam hall ",description = "only admin can add seats to  examhalls ")
    public String addSeatToExamHall(@RequestBody SeatDTO dto){
        return hallAllocationService.addSeatsToHall(dto);
    }

    @PostMapping("/allocate")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "automatically allocate exam hall and seat no to all students",description = "only admin can allcoate setas and examhalls to all students regsietred for this exam")
    public String allocateHallandSeat(@RequestParam String examName){
        return hallAllocationService.allocateHallAndSeatToStudents(examName);
    }

}

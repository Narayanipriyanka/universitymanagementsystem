package com.example.attendanceservice.controller;

import com.example.attendanceservice.dto.LeaveDTO;
import com.example.attendanceservice.entity.Leave;
import com.example.attendanceservice.servcie.LeaveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance/leaves")
@Tag(name="University Leave controller",description = "faculty can apply for leave ,admin can approve leave here")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;
    @PostMapping
    @PreAuthorize("hasRole('FACULTY')")
    @Operation(summary = "any faculty can apply for faculty leaves")
    public String applyForLeave(@RequestBody LeaveDTO leaveDTO){
        return leaveService.applyForLeave(leaveDTO);
    }
    @PutMapping("/approve")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin approve pending faculty leaves")
    public String approveLeaves(){
        return leaveService.approveLeaves();
    }
    @GetMapping("/pending")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "admin will get all pending faculty leaves")
    public List<Leave> getallPending(){
        return leaveService.getAllPendingLeaves();
    }

}

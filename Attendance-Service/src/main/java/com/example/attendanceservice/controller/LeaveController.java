package com.example.attendanceservice.controller;

import com.example.attendanceservice.dto.LeaveDTO;
import com.example.attendanceservice.entity.Leave;
import com.example.attendanceservice.servcie.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance/leaves")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;
    @PostMapping

    public String applyForLeave(@RequestBody LeaveDTO leaveDTO){
        return leaveService.applyForLeave(leaveDTO);
    }
    @PutMapping("/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public String approveLeaves(){
        return leaveService.approveLeaves();
    }
    @GetMapping("/pending")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Leave> getallPending(){
        return leaveService.getAllPendingLeaves();
    }

}

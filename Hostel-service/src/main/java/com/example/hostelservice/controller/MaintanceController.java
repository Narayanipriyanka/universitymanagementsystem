package com.example.hostelservice.controller;

import com.example.hostelservice.dto.MaintananceRequestDTO;
import com.example.hostelservice.entity.MaintananceRequest;
import com.example.hostelservice.service.MaintananceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hostel/maintaninance")
@Tag(name = "Maintanance issues controller")
public class MaintanceController {
    @Autowired
    private MaintananceService maintananceService;
    @PutMapping("/solveIssues")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "solve maintanace issue",description = "Only admin can access this api and solve the pending mmiantannace issues")
    public String solveIssueIn(@RequestParam String issueIn){
        return maintananceService.solveMaintanaceIssue(issueIn);
    }
    @PutMapping("/raiseIssues")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "raise maintanace issue",description = "Only student can access this api and raise the  mmiantannace issues")
    public String raiseIssueIn(@RequestBody MaintananceRequestDTO dto){
        return maintananceService.raiseMaintananceIssue(dto);
    }
    @GetMapping("/pendingIssues")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "get all list of pending maintanace issue",description = "Only admin can access this api and get the list of pending mmiantannace issues")
    public List<MaintananceRequest> getPendingMaintananceIssues(){
        return maintananceService.getPendingMaintanceReq();
    }
    
}

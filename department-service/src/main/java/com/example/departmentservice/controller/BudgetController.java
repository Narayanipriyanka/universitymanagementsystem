package com.example.departmentservice.controller;

import com.example.departmentservice.DTO.BudgetDTO;
import com.example.departmentservice.DTO.ResourceDTO;
import com.example.departmentservice.entity.Resources;
import com.example.departmentservice.service.DepartmentService;
import jakarta.mail.Quota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department/budget")
public class BudgetController {
    @Autowired
    private DepartmentService departmentService;
    @GetMapping("/remainingBudget")
    @PreAuthorize("hasRole('ADMIN')")
    public Double getRemainingBudget(@RequestParam String deptcode){
        return departmentService.getRemainingAmountOfDept(deptcode);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String addBudget(@RequestBody BudgetDTO dto){
        return departmentService.addBudget(dto);
    }
    @PostMapping("/resource")
    @PreAuthorize("hasRole('ADMIN')")
    public String addResource(@RequestBody ResourceDTO dto){
        return departmentService.addNewResource(dto);
    }
    @PutMapping("/allocate")
    @PreAuthorize("hasRole('ADMIN')")
    public String allocateResource(@RequestParam String deptcode,@RequestParam Long resourceId){
        return departmentService.allocateResource(deptcode,resourceId);
    }
    @GetMapping("/allcoated")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Resources> getALLAllocatedResources(){
        return departmentService.getAllAllocatedResources();
    }
}

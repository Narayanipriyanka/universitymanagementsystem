package com.example.departmentservice.controller;

import com.example.departmentservice.DTO.BudgetDTO;
import com.example.departmentservice.DTO.ResourceDTO;
import com.example.departmentservice.entity.Resources;
import com.example.departmentservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.Quota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department/budget")
@Tag(name = "university budget controller")
public class BudgetController {
    @Autowired
    private DepartmentService departmentService;
    @GetMapping("/remainingBudget")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can get reamining budget of a depatment here")
    public Double getRemainingBudget(@RequestParam String deptcode){
        return departmentService.getRemainingAmountOfDept(deptcode);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can add budget of a depatment here")
    public String addBudget(@RequestBody BudgetDTO dto){
        return departmentService.addBudget(dto);
    }

    @PostMapping("/resource")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can add resources to university here")
    public String addResource(@RequestBody ResourceDTO dto){
        return departmentService.addNewResource(dto);
    }

    @PutMapping("/allocate")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can allcoate resources to a depatment here")
    public String allocateResource(@RequestParam String deptcode,@RequestParam Long resourceId){
        return departmentService.allocateResource(deptcode,resourceId);
    }
    @GetMapping("/allcoated")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can get all allcoated resources  of  depatments here")
    public List<Resources> getALLAllocatedResources(){
        return departmentService.getAllAllocatedResources();
    }
}

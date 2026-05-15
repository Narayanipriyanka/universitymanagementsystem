package com.example.departmentservice.controller;

import com.example.departmentservice.DTO.DepartmentDTO;
import com.example.departmentservice.DTO.ProgramDTO;
import com.example.departmentservice.entity.Program;
import com.example.departmentservice.service.DepartmentProducer;
import com.example.departmentservice.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
@Tag(name="University Department Controller")
public class DepartmentController {
@Autowired
    private DepartmentProducer departmentProducer;
@Autowired
    private DepartmentService service;

@PostMapping
@PreAuthorize("hasRole('ADMIN')")
@Operation(summary = "only admin can add a depatment here")
    public String addDepartment(DepartmentDTO dept){
     return departmentProducer.addDepartment(dept);
 }

    @PostMapping("/programs")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can add program  here")
    public String addProgram(ProgramDTO program){
        return service.addProgram(program);
    }
    @GetMapping("/programs")
   @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "anyone can get list of programs avalilable in university here")
    public List<Program> getAllPrograms(){
    return service.getAllPrograms();
    }
}

package com.example.departmentservice.service;

import com.example.departmentservice.DTO.BudgetDTO;
import com.example.departmentservice.DTO.ProgramDTO;
import com.example.departmentservice.DTO.ResourceDTO;
import com.example.departmentservice.entity.*;
import com.example.departmentservice.repository.BudgetRepository;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.repository.ProgramRepository;
import com.example.departmentservice.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
@Autowired
    private DepartmentRepository repository;
@Autowired
private ProgramRepository programRepository;
@Autowired
private BudgetRepository budgetRepository;
@Autowired
private ResourceRepository resourceRepository;
    public String addProgram(ProgramDTO program) {
        List<Department> depts=repository.findAllByDeptIdIn(program.getDepartment_ids());
        Program p=new Program();
        p.setName(program.getName());
        p.setDepartments(depts);
        p.setDuration(program.getDuration());
        p.setNofSems(program.getNofSems());
        programRepository.save(p);
        return "program added successfully";
    }
    public List<Program> getAllPrograms(){
        return programRepository.findAll();
    }
    public String addBudget(BudgetDTO dto){
        Budget b=new Budget();
        Department d=repository.findByDepartmentCode(dto.getDepCode());
        b.setTotalAmount(dto.getTotalAmount());
        b.setUsedAmount(0.00);
        b.setRemainingAmount(dto.getTotalAmount());
        b.setDepartment(d);
        b.setYear(dto.getYear());
        budgetRepository.save(b);
        return "budget addedd successfully to "+dto.getDepCode();
    }
   public String addNewResource(ResourceDTO dto){
       Resources r=new Resources();
       r.setName(dto.getName());
       r.setPrice(dto.getPrice());
       r.setType(dto.getType());
       r.setCount(dto.getCount());
       r.setStatus(AllocationStatus.AVAILABLE);
       resourceRepository.save(r);
       return "new resource added successfully";

   }
   public String allocateResource(String deptCode,Long id){
        Resources r=resourceRepository.findById(id).orElseThrow(()-> new RuntimeException("no resources found with this id"));
    Department d= repository.findByDepartmentCode(deptCode);
    Budget b=budgetRepository.findByDepartment(d);
    if(b.getUsedAmount()+r.getPrice()<b.getRemainingAmount()) {
        r.setAllocatedDept(d);
        r.setStatus(AllocationStatus.ALLOCATED);
        b.setUsedAmount(b.getUsedAmount() + r.getPrice());
        b.setRemainingAmount(b.getTotalAmount() - (b.getUsedAmount() + r.getPrice()));
        budgetRepository.save(b);
        resourceRepository.save(r);
        return "resource " + r.getName() + "is allcoated to " + d.getDepartmentCode() + r.getType() + " now the current budget of this deprtment is" + b.getRemainingAmount();
    }else{
        return "Low budget cant allocate resource becouase the department has less budget to buy them";
    }
    }
    public List<Resources> getAllAllocatedResources(){
        return resourceRepository.findAllByStatus(AllocationStatus.ALLOCATED);
    }
    public Double getRemainingAmountOfDept(String deptCode){
        Department d=repository.findByDepartmentCode(deptCode);
        Budget b=budgetRepository.findByDepartment(d);
        return b.getRemainingAmount();
    }
}

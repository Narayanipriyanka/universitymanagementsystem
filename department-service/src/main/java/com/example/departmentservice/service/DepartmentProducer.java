package com.example.departmentservice.service;

import com.example.departmentservice.DTO.DepartmentDTO;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.events.DepartmentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DepartmentProducer {
    private final DepartmentRepository departmentRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    public DepartmentProducer(KafkaTemplate<String, Object> kafkaTemplate,
                              DepartmentRepository departmentRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.departmentRepository = departmentRepository;
    }
    public String addDepartment(DepartmentDTO dept){
        Department department=new Department();
        department.setDepartmentCode(dept.getDepartmentCode());
        department.setDepartmentFullName(dept.getDepartmentFullName());
        department.setHodId(dept.getHodId());
        department.setHodName(dept.getHodName());
        departmentRepository.save(department);
        DepartmentEvent dto=new DepartmentEvent(dept.getDepartmentFullName(),dept.getDepartmentCode(),dept.getHodId(),dept.getHodName());
        kafkaTemplate.send("departments",dto);
        return "departemnt added successfully";
    }

}

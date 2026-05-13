package com.example.examservice.controller;

import com.example.examservice.service.ExamService;
import com.example.examservice.service.HallAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exams")
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private HallAllocationService hallAllocationService;


}

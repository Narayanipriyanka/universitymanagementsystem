package com.example.feemanagementservice.controller;

import com.example.feemanagementservice.dto.PaymentDTO;
import com.example.feemanagementservice.service.FeesService;
import com.razorpay.RazorpayException;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fees")
public class FeeController {
    @Autowired
    private FeesService feesService;
    @PutMapping("/pay")
    @PreAuthorize("hasRole('STUDENT')")
    public String payFees(PaymentDTO dto) throws RazorpayException {
        return feesService.payFees(dto);
    }


}

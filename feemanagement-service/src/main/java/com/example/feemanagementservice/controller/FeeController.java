package com.example.feemanagementservice.controller;

import com.example.feemanagementservice.dto.FeeStructureDTO;
import com.example.feemanagementservice.dto.PaymentDTO;
import com.example.feemanagementservice.entity.PaymentFor;
import com.example.feemanagementservice.service.FeesService;
import com.razorpay.RazorpayException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/fees")
public class FeeController {
    @Autowired
    private FeesService feesService;
    @PutMapping("/pay")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = " fee payment",description = " only studentc can access this and does their paymnet ")
    public String payFees(@RequestBody PaymentDTO dto) throws RazorpayException {
        return feesService.payFees(dto);
    }
   @PostMapping("/feeStructure")
   @PreAuthorize("hasRole('ADMIN')")
   @Operation(summary = "add fees to a semester in aprogram",description = " only admin can access this and adds fees sturcuture to a course in a semester")
   public String addFeeStructure(@RequestBody FeeStructureDTO dto){
       return feesService.addFeeStructure(dto);
   }
   @GetMapping("/feesTracking")
   @PreAuthorize("hasRole('STUDENT')")
   @Operation(summary = "get current status of fee payment",description = " only studentc can access this and tracks his paymnet status")
    public String getFeePaymentStatus(@RequestParam UUID studenrId,@RequestParam PaymentFor category){
        return feesService.feePaymentTracking(studenrId,category);
   }
    @GetMapping("/feesstructure")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "get fees of different semesters in a program",description = " only studentc can access check the fee structure of a program")
    public String getFeeStructure(@RequestParam String programCOde,@RequestParam String deptCode){
        return feesService.getFeeStructureOfProgram(programCOde,deptCode);
    }


}

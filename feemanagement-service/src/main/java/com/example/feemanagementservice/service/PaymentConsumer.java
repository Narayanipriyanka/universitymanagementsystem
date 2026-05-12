package com.example.feemanagementservice.service;

import com.example.events.PayFeesEvent;
import com.example.feemanagementservice.entity.Payment;
import com.example.feemanagementservice.entity.PaymentFor;
import com.example.feemanagementservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {
    @Autowired
    private PaymentRepository paymentRepository;

    @KafkaListener(topics="payFees",groupId = "pay-fees")
    public void consumePayFees(PayFeesEvent dto){
        Payment p=new Payment();
        p.setAmount(dto.getAmount());
        if(dto.getPayingFor().equals("LIBRARY_FEE")){
        p.setCategory(PaymentFor.LIBRARY_FEE);}
        else if (dto.getPayingFor().equals("HOSTEL_FEE")) {
            p.setCategory(PaymentFor.HOSTEL_FEE);}
        else if (dto.getPayingFor().equals("MESS_FEE")) {
            p.setCategory(PaymentFor.MESS_FEE);}
        else if (dto.getPayingFor().equals("TUTION_FEE")) {
            p.setCategory(PaymentFor.TUTION_FEE);}
        else{
            p.setCategory(PaymentFor.TUTION_FEE);}
        p.setStudentId(dto.getStudentId());
       paymentRepository.save(p);

    }
    }


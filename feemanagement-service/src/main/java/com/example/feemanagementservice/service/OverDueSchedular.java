package com.example.feemanagementservice.service;

import com.example.events.OverDueAlert;
import com.example.feemanagementservice.entity.FeeRecord;
import com.example.feemanagementservice.repository.FeeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverDueSchedular {
    @Autowired
    private FeeRecordRepository feeRecordRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public OverDueSchedular(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(cron="0 0 9 * * ?")
    public void sendOverdueAlerts(){
        List<FeeRecord> feeRecords=feeRecordRepository.findAll();
        for(FeeRecord r:feeRecords){
            if(r.getTotalbalance()/(r.getTotalbalance()+r.getPaid())*100<=50){
                OverDueAlert dto=new OverDueAlert(r.getStudentId(),r.getPaid(),r.getTutionFeeBalance(),r.getExamFeeBalance(),r.getHostelFeeBalance(),r.getRecordFeeBalance(),r.getMessFeeBalance(),r.getTotalbalance());
                kafkaTemplate.send("sendOverDueAlert",dto);
            }
        }
    }
}

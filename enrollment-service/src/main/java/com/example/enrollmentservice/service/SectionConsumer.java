package com.example.enrollmentservice.service;

import com.example.enrollmentservice.entity.Section;
import com.example.enrollmentservice.repository.SectionRepository;
import com.example.events.MaterialEvent;
import com.example.events.SectionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SectionConsumer {
    @Autowired
    private SectionRepository sectionRepository;
    @KafkaListener(topics="sendMaterial",groupId="enroll-section-group")
    public void consume(SectionEvent event){
        Section s=new Section();
        s.setSectionId(event.getSectionId());
        s.setCapacity(event.getCapacity());
        s.setRoom(event.getRoom());
        s.setCourseCode(event.getCourseCode());
        sectionRepository.save(s);
    }
}

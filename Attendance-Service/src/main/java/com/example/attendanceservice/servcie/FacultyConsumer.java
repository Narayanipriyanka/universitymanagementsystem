package com.example.attendanceservice.servcie;

import com.example.attendanceservice.entity.FacultyDetails;
import com.example.attendanceservice.repository.FacultyDetailsRepository;
import com.example.events.FacultyCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FacultyConsumer {
    @Autowired
    private FacultyDetailsRepository facultyDetailsRepository;
    @KafkaListener(topics="sendFacultyCreated",groupId="notification-facultycreated-group")
    public void consumeFacultyCreated(FacultyCreatedEvent request) throws Exception {
        FacultyDetails f=new FacultyDetails();
        f.setFacultyId(request.getFacultyId());
        f.setUsername(request.getUsername());
        f.setEmail(request.getEmail());
        f.setPassword(request.getPassword());
        facultyDetailsRepository.save(f);
    }

}

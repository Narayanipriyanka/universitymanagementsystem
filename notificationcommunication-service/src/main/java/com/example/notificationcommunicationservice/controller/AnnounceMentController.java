package com.example.notificationcommunicationservice.controller;

import com.example.notificationcommunicationservice.dto.ParentCommunicationDTO;
import com.example.notificationcommunicationservice.entity.Circulars;
import com.example.notificationcommunicationservice.service.AnnouncementService;
import com.example.notificationcommunicationservice.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@Tag(name="University Announcement Controller")
public class AnnounceMentController {
    @Autowired
    private AnnouncementService announceMentService;

    @PostMapping("/circular")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can send circular or annoouncement or emergency alerts here")
    public String addCircular(Circulars circular){
        announceMentService.sendCircular(circular);
        return "circular send to everyone";
    }
    @PostMapping("/parentcommunication")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "only admin can send mails to parent regarding any issue with student here")
    public String communicate(ParentCommunicationDTO dto){
       return announceMentService.parentCommunication(dto);
    }

}

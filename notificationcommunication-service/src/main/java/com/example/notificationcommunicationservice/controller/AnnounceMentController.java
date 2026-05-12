package com.example.notificationcommunicationservice.controller;

import com.example.notificationcommunicationservice.entity.Circulars;
import com.example.notificationcommunicationservice.service.AnnouncementService;
import com.example.notificationcommunicationservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class AnnounceMentController {
    @Autowired
    private AnnouncementService announceMentService;

    @PostMapping("/circular")
    @PreAuthorize("hasRole('ADMIN')")
    public String addCircular(Circulars circular){
        announceMentService.sendCircular(circular);
        return "circular send to everyone";
    }
}

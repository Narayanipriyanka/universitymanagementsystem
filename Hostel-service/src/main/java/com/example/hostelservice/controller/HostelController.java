package com.example.hostelservice.controller;

import com.example.hostelservice.dto.HostelDTO;
import com.example.hostelservice.dto.RoomDTO;
import com.example.hostelservice.dto.VisitorDTO;
import com.example.hostelservice.entity.HostelType;
import com.example.hostelservice.service.HostelService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/hostel")
public class HostelController {
    @Autowired
    private HostelService hostelService;
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "add hostel",description = "Only for admin he can add a hostel ")
    public String addHostel(@RequestBody HostelDTO dto){
        return hostelService.addHostel(dto);
    }
    @PostMapping("/room")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "add room",description = "Only admin can add room in hostel")
    public String addRoom(@RequestBody RoomDTO dto){
        return hostelService.addRoom(dto);
    }
    @GetMapping("/availableRooms")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "all available rooms in hostel",description = "Only admin get all the available rooms automatically")
    public Integer getAvailableRooms(@RequestParam HostelType type){
        return hostelService.getAvailableRooms(type);
    }
    @GetMapping("/filled")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "filled rooms in hostel",description = "Only for admin he will be get details of allcoated  rooms ")
    public String getFilledCount(@RequestParam HostelType dto){
        return hostelService.getFilledCount(dto);
    }
    @PutMapping("/joinHostel")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "join in to hostel",description = "Only for student he will be allcoated a room among available rooms automatically")
    public String addStudentToRoom(@RequestParam UUID studentID,@RequestParam HostelType type){
        return hostelService.allocateRoomToStudent(studentID,type);
    }
    @PostMapping("/visitor")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "add visitor info into logs",description = "Only for the student  for whom the visitor has come")
    public String addVisitorLogs(@RequestBody VisitorDTO dto){
        return hostelService.addVisitorLog(dto);
    }

}

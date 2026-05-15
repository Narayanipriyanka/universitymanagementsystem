package com.example.timtableservice.controller;

import com.example.timtableservice.dto.RoomDTO;
import com.example.timtableservice.service.TimeTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/timetable")
@Tag(name = "University TimeTable and classroom controller")
public class TimeTableController {
    @Autowired
    private TimeTableService timeTableService;

    @PostMapping("/room")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "add room", description = "only admin can add class room")
    public String addRoom(@RequestBody RoomDTO dto){
        return timeTableService.addClassRoom(dto);
    }

    @PostMapping("/resource")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "add resources to room", description = "only admin can add resources to class room")
    public String addResourcesToRoom(@RequestParam String roomNo,@RequestParam String resourceName,@RequestParam Integer count,@RequestParam Boolean isElectronc){
        return timeTableService.addResourcesToClassRoom(roomNo,resourceName,count,isElectronc);
    }

    @GetMapping("/facultyAvailable")
    @Operation(summary = "get faculty availablity", description = "any one canc heck faculty availablity")
    public String getFacultyavilableHours(UUID facultyId){
        return timeTableService.getAvailableHours(facultyId);
    }
    @PostMapping("/schedule")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "automatically generate timetable based on available rooms and courses,faculty", description = "only adminc an schedule timetable")
    public String scheduleTimeTable(){
        return timeTableService.classScheduling();
    }
    @GetMapping
    @Operation(summary = "get timetable by roomNo", description = "anyone can get timetable")
    public String getTimeTable(String roomNo){
        return timeTableService.getTimeTable(roomNo);
    }
    @GetMapping("/facultyTimeTable")
    @PreAuthorize("hasRole('FACULTY')")
    @Operation(summary = "get faculty's classroom timetable", description = "only faculty can get information about classroom no and class timings ")
    public String getClassRoomTimeTable(UUID facultyId){
        return timeTableService.getFacultyClassRoom(facultyId);
    }

}

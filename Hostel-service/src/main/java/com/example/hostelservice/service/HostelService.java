package com.example.hostelservice.service;

import com.example.hostelservice.dto.HostelDTO;
import com.example.hostelservice.dto.RoomDTO;
import com.example.hostelservice.entity.Hostel;
import com.example.hostelservice.entity.HostelType;
import com.example.hostelservice.entity.Room;
import com.example.hostelservice.entity.RoomAllocation;
import com.example.hostelservice.repository.HostelRepository;
import com.example.hostelservice.repository.RoomAllocationRepository;
import com.example.hostelservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HostelService {
    @Autowired
    private HostelRepository hostelRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomAllocationRepository allocationRepository;

    public String addHostel(HostelDTO dto){
        Hostel h=new Hostel();
        h.setFee(dto.getFee());
        h.setNoOfTRooms(dto.getNoOfTRooms());
        h.setType(dto.getType());
        hostelRepository.save(h);
        return "hostel added successfully";
    }
    public String addRoom(RoomDTO dto){
        Room h=new Room();
        Hostel hostel=hostelRepository.findById(dto.getHostelId()).orElseThrow(()->new RuntimeException("no hostel found with this id"));
        h.setNoOfBeds(dto.getNoOfBeds());
     h.setFilledCount(0);
     h.setHostel(hostel);
        roomRepository.save(h);
        return "room added successfully to"+hostel.getId()+hostel.getType()+"hostel";
    }
    public Integer getAvailableRooms(HostelType type){
        List<Hostel> hostels=hostelRepository.findByType(type);
        List<Room> total=new ArrayList<>();
        Integer available=0;
        for(Hostel h:hostels){
        List<Room> rooms=h.getRooms();
        total.addAll(rooms);
        }
        for(Room r:total){
            if(r.getNoOfBeds()-r.getFilledCount()>0){
                available=available+1;
            }
        }
        return available;
    }
    public String getFilledCount(HostelType type){
        List<Hostel> hostels=hostelRepository.findByType(type);
        List<Room> total=new ArrayList<>();
        Integer filled=0;
        Integer all=0;
        StringBuilder fill= new StringBuilder(new String());
        for(Hostel h:hostels){
            List<Room> rooms=h.getRooms();
            total.addAll(rooms);
        }
        for(Room r:total){
            filled+=r.getFilledCount();
            all+=r.getNoOfBeds();
            fill.append("room No:").append(r.getRoomNo()).append("\n Total beds:").append(all).append("\n filled beds:").append(filled);
        }
        return fill.append("total filled beds are: ").append(filled).append("\n total available beds: ").append(all).append("\ntotal remaining beds ").append(all - filled).toString();
    }
    public String allocateRoomToStudent(UUID studentId,HostelType type){
        List<Hostel> hostels=hostelRepository.findByType(type);
        List<Room> total=new ArrayList<>();
        RoomAllocation a=new RoomAllocation();
        for(Hostel h:hostels){
            List<Room> rooms=h.getRooms();
            total.addAll(rooms);
        }
        for(Room r:total){
            if(r.getNoOfBeds()-r.getFilledCount()>0){
                r.setFilledCount(r.getFilledCount()+1);
                roomRepository.save(r);
                a.setAllocated(true);
                a.setStudentId(studentId);
                a.setRoomNo(r.getRoomNo());
                allocationRepository.save(a);
                break;
            }
        }
        return "student is added into room no"+a.getRoomNo();
    }

}

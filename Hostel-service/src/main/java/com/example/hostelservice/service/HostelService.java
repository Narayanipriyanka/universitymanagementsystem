package com.example.hostelservice.service;

import com.example.events.PayFeesEvent;
import com.example.hostelservice.dto.HostelDTO;
import com.example.hostelservice.dto.MessDTO;
import com.example.hostelservice.dto.RoomDTO;
import com.example.hostelservice.dto.VisitorDTO;
import com.example.hostelservice.entity.*;
import com.example.hostelservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private MessRepository messRepository;
    @Autowired
    private MealRepsoitory mealRepsoitory;
    @Autowired
    private VisitorLogsRepository visitorLogsRepository;
    @Autowired
    private RoomAllocationRepository allocationRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    public HostelService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

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
    public List<Room> getAvailableRooms(HostelType type){
        List<Hostel> hostels=hostelRepository.findAllByType(type);
        List<Room> total=new ArrayList<>();
        List<Room> totalAvailale=new ArrayList<>();
        Integer available=0;
        for(Hostel h:hostels){
        List<Room> rooms=h.getRooms();
        total.addAll(rooms);
        }
        for(Room r:total){
            if(r.getNoOfBeds()-r.getFilledCount()>0){
                available=available+1;
                totalAvailale.add(r);
            }
        }
        return totalAvailale;
    }
    public List<Hostel> getListOfHostels(HostelType type){
        return hostelRepository.findAllByType(type);
    }
    public String getFilledCount(HostelType type){
        List<Hostel> hostels=hostelRepository.findAllByType(type);
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
                PayFeesEvent feesEvent=new PayFeesEvent(studentId,r.getHostel().getFee(),"HOSTEL_FEE");
                kafkaTemplate.send("payFees",feesEvent);
                break;
            }
        }
        return "student is added into room no"+a.getRoomNo();
    }

    public String addVisitorLog(VisitorDTO dto){
        VisitorLogs logs=new VisitorLogs();
        logs.setEntryTime(dto.getEntryTime());
        logs.setVisitDate(LocalDate.now());
        logs.setExitTime(dto.getExitTime());
        logs.setStudentID(dto.getStudentID());
        logs.setVisitorName(dto.getVisitorName());
        logs.setReason(dto.getReason());
        logs.setRelation(dto.getRelation());
        visitorLogsRepository.save(logs);
        return "visitor log added successfully";
    }
    public String addMess(MessDTO dto){
        Mess m=new Mess();
        m.setMonthlyFee(dto.getMonthlyFee());
        m.setOnlyForHostelers(dto.getOnlyForHostelers());
        m.setType(dto.getType());
        messRepository.save(m);
        return "mess added successfully with id"+m.getId();
    }
    public List<MessDTO> getAllMessDetails(){
        List<Mess> m=messRepository.findAll();
        List<MessDTO> dto=new ArrayList<>();
        for(Mess mess:m){
            MessDTO messDTO=new MessDTO();
            messDTO.setType(mess.getType());
            messDTO.setMonthlyFee(mess.getMonthlyFee());
            messDTO.setOnlyForHostelers(mess.getOnlyForHostelers());
            dto.add(messDTO);
        }
        return dto;
    }
    public String selectAMess(UUID studentId,MessDTO dto){
        List<Mess> m=messRepository.findByType(dto.getType());
        for(Mess mess:m){
            if(mess.getOnlyForHostelers()==dto.getOnlyForHostelers()&&mess.getMonthlyFee()==dto.getMonthlyFee()){
                List<UUID> studentIds=mess.getStudentIds();
                studentIds.add(studentId);
                mess.setStudentIds(studentIds);
                messRepository.save(mess);
                PayFeesEvent feesEvent=new PayFeesEvent(studentId,mess.getMonthlyFee(),"MESS_FEE");
                kafkaTemplate.send("payFees",feesEvent);
                return "mess selected successfully your mess id is"+mess.getId();
            }
        }
        return " ";
    }
    public String addMealPLan(Long messId,MealType type){
        Mess mess=messRepository.findById(messId).orElseThrow(()->new RuntimeException("no mess found with this id"));
        Meal m=new Meal();
        m.setMealType(type);
        m.setMessId(messId);
        if(type==MealType.BASIC)
            m.setMonthlyFee(mess.getMonthlyFee());
        else if (type==MealType.SPECIAL) {
            m.setMonthlyFee(mess.getMonthlyFee()+2000);
        }
        mealRepsoitory.save(m);
        return "meal plan added successfully";
    }
    public String selectMealPlan(UUID studentId,Long messId,MealType type){
       List<Meal> meal=mealRepsoitory.findAllByMessId(messId);
       Mess mess=messRepository.findById(messId).orElseThrow(()->new RuntimeException("no mess found with this id"));
        for(Meal m:meal){
            if(m.getMealType()==type){
                List<UUID> studentIds=m.getStudentIds();
                studentIds.add(studentId);
                m.setStudentIds(studentIds);
                if(type==MealType.BASIC)
                    m.setMonthlyFee(mess.getMonthlyFee());
                else if (type==MealType.SPECIAL) {
                    m.setMonthlyFee(mess.getMonthlyFee()+2000);
                }
                mealRepsoitory.save(m);
                return "meal selected successfully your meal id is"+m.getId()+",your monthly mess fee is:"+m.getMonthlyFee();
            }
        }
        return " ";
    }
    public String noOFVegOrNonvegStudents(MessType messType){
        List<Mess> m=messRepository.findAllByType(messType);
        int total=0;
        for(Mess mess:m){
            total+=mess.getStudentIds().size();
        }
        return "total "+total+" students are eating"+messType+" food";
    }
    public String dropFromMealPlan(UUID studentID,Long mealID){
        Meal m=mealRepsoitory.findById(mealID).orElseThrow(()->new RuntimeException("no meal plan found with this id"));
        List<UUID> studentIds=m.getStudentIds();
        studentIds.remove(studentID);
        m.setStudentIds(studentIds);
        mealRepsoitory.save(m);
        return "dropped from "+m.getMealType()+"plan successfully";

    }

}

package com.example.examservice.service;

import com.example.events.ExamHallDetailsEvent;
import com.example.examservice.dto.ExamHallDTO;
import com.example.examservice.dto.SeatDTO;
import com.example.examservice.entity.ExamHall;
import com.example.examservice.entity.ExamRegister;
import com.example.examservice.entity.Seat;
import com.example.examservice.repository.ExamRegisterRepository;
import com.example.examservice.repository.HallRepository;
import com.example.examservice.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HallAllocationService {
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private ExamRegisterRepository examRegisterRepository;
    @Autowired
    private KafkaTemplate<String ,Object> kafkaTemplate;

    public String addHall(ExamHallDTO dto){
        ExamHall examHall=hallRepository.findByHallNo(dto.getHallNo());
        if(examHall!=null){
            throw new RuntimeException("already a hall present with this hall number");
        }
        ExamHall hall=new ExamHall();
        hall.setHallNo(dto.getHallNo());
        hall.setFloor(dto.getFloor());
        hall.setHallCapacity(dto.getHallCapacity());
        hall.setTotalNoOfallocated(0);
        hall.setAvailable(true);
        hallRepository.save(hall);
        return " Hall added successfully";
    }
    public String addSeatsToHall(SeatDTO dto){
        ExamHall hall=hallRepository.findByHallNo(dto.getHallNo());
        List<Seat> seatList=new ArrayList<>();
        for(int i=0;i<hall.getHallCapacity();i++){
            Seat s=new Seat();
            s.setExamHall(hall);
            s.setAllocated(false);
            seatRepository.save(s);
            seatList.add(s);
        }
        hall.setSeatList(seatList);
        hallRepository.save(hall);
        return "seats added successfully to "+dto.getHallNo();
    }
    public String allocateHallAndSeatToStudents(String examName){
        ExamRegister examRegisters=examRegisterRepository.findByExamName(examName);
        List<ExamHall> examHalls=hallRepository.findAllByIsAvailable(true);
        String hallAllocated=" ";
        Long seatAllocated= 0L;
        for(UUID studentId:examRegisters.getStudentIds()){
           ExamHall hall=examHalls.getFirst();
           hallAllocated=hall.getHallNo();
           examHalls.remove(hall);
           List<Seat> s=seatRepository.findByExamHallAndIsAllocated(hallAllocated,false);
           Seat seat=s.getFirst();
           seat.setAllocated(true);
           seat.setStudentId(studentId);
           seatAllocated=seat.getSeatNo();
            ExamHallDetailsEvent dto=new ExamHallDetailsEvent(studentId,hallAllocated,seatAllocated,hall.getFloor());
            kafkaTemplate.send("sendExamHallDetails",dto);

        }
        return "Hall allocated successfully to all registered students";
    }

}

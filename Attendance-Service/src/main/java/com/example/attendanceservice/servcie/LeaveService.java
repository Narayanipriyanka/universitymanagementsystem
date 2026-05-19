package com.example.attendanceservice.servcie;

import com.example.attendanceservice.dto.LeaveDTO;
import com.example.attendanceservice.entity.AttendanceReport;
import com.example.attendanceservice.entity.FacultyDetails;
import com.example.attendanceservice.entity.Leave;
import com.example.attendanceservice.entity.LeaveStatus;
import com.example.attendanceservice.repository.AttendanceReportRepository;
import com.example.attendanceservice.repository.FacultyDetailsRepository;
import com.example.attendanceservice.repository.LeaveRepository;
import com.example.events.LeaveEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {
    @Autowired
    private LeaveRepository leaveRepository;
    @Autowired
    private AttendanceReportRepository attendanceReportRepository;
    @Autowired
    private FacultyDetailsRepository facultyDetailsRepository;
private final KafkaTemplate<String, Object> kafkaTemplate;

    public LeaveService(KafkaTemplate<String,Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String applyForLeave(LeaveDTO leave){
        Leave leave1=leaveRepository.findByStartDateAndFacultyID(leave.getStartDate(),leave.getFacultyId());
        if(leave1!=null){
            throw new RuntimeException("already applied leave for this date");
        }
        Leave l=new Leave();
        l.setLeaveReason(leave.getLeaveReason());
        l.setLeaveStatus(LeaveStatus.PENDING);
        l.setStartDate(leave.getStartDate());
        l.setEndDate(leave.getEndDate());
        l.setNoOfDays(leave.getNoOfDays());
        l.setFacultyId(leave.getFacultyId());
       leaveRepository.save(l);
       return "successfully applied leave for "+leave.getNoOfDays()+ " status is "+l.getLeaveStatus();
    }
    public List<Leave> getAllPendingLeaves(){
        List<Leave> l=leaveRepository.findAllByLeaveStatus(LeaveStatus.PENDING);
        return l;
    }
    public String approveLeaves(){
        List<Leave> l=getAllPendingLeaves();
        for(Leave leave:l){
            List<Leave> facultyLeaves=leaveRepository.findAllByFacultyId(leave.getFacultyId());
            if(facultyLeaves.size()>10){
                leave.setLeaveStatus(LeaveStatus.REJECTED);
                leaveRepository.save(leave);
                FacultyDetails f=facultyDetailsRepository.findById(leave.getFacultyId()).orElseThrow(()->new RuntimeException("no faculty found with this id"));
                LeaveEvent leaveEvent=new LeaveEvent(f.getFacultyId(),f.getEmail(),"REJECTED");
                kafkaTemplate.send("leave",leaveEvent);
            }
            else{
                leave.setLeaveStatus(LeaveStatus.APPROVED);
                leaveRepository.save(leave);

                List<AttendanceReport> ar=attendanceReportRepository.findAllByFacultyId(leave.getFacultyId());
                for(AttendanceReport a:ar){
                    if(a.getMonth().equalsIgnoreCase(leave.getStartDate().getMonth().toString())){
                       a.setAbsent(a.getAbsent()+ leave.getNoOfDays());
                       a.setLeave(a.getLeave()+ leave.getNoOfDays());
                        attendanceReportRepository.save(a);
                    }
                }
                FacultyDetails f=facultyDetailsRepository.findById(leave.getFacultyId()).orElseThrow(()->new RuntimeException("no faculty found with this id"));
                LeaveEvent leaveEvent=new LeaveEvent(f.getFacultyId(),f.getEmail(),"APPROVED");
                kafkaTemplate.send("leave",leaveEvent);

            }
        }
        return "leaves are approved successfully";

    }

}

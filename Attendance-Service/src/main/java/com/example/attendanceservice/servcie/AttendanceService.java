package com.example.attendanceservice.servcie;

import com.example.attendanceservice.entity.Attendance;
import com.example.attendanceservice.entity.AttendanceReport;
import com.example.attendanceservice.entity.AttendanceStatus;
import com.example.attendanceservice.entity.FacultyDetails;
import com.example.attendanceservice.repository.AttendanceReportRepository;
import com.example.attendanceservice.repository.AttendanceRepository;
import com.example.attendanceservice.repository.FacultyDetailsRepository;
import com.example.events.AttendanceReportEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private AttendanceReportRepository attendanceReportRepository;
@Autowired
private FacultyDetailsRepository facultyDetailsRepository;
private final KafkaTemplate<String,Object> kafkaTemplate;

    public AttendanceService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String addAttendance(){
        String username=getUserName();
        FacultyDetails f=facultyDetailsRepository.findByUsername(username);
        Attendance a=new Attendance();
        a.setDate(LocalDate.now());
        a.setFacultyId(f.getFacultyId());
        a.setStatus(AttendanceStatus.PRESENT);
        attendanceRepository.save(a);
        return "attendance added to "+f.getUsername()+"for date"+a.getDate();
    }
    public List<Attendance> getAttendanceOfFaculty(UUID facultyId){
        return attendanceRepository.findAllByFacultyId(facultyId);
    }
    public int noOfAbsents(UUID facultyId,String month){
        List<Attendance> attend=attendanceRepository.findAllByFacultyId(facultyId);
        int present=0;
        for(Attendance attendance:attend) {
            if(attendance.getDate().getMonth().toString().equalsIgnoreCase(month)&&attendance.getStatus().equals(AttendanceStatus.PRESENT)){
                present++;
            }
        }

        int noOfDays=getDaysInMonth(month);
        AttendanceReport a=new AttendanceReport();
        a.setMonth(month);
        a.setFacultyId(facultyId);
        a.setPresent(present);
        a.setAbsent(noOfDays-present);
        a.setTotalDays(noOfDays);
    attendanceReportRepository.save(a);
        return noOfDays-present;
        }

        public AttendanceReport getAttendanceReportOfMonth(UUID facultyId,String month){
        List<AttendanceReport> ar=attendanceReportRepository.findAllByFacultyId(facultyId);
        for(AttendanceReport a:ar){
            if(a.getMonth().equalsIgnoreCase(month)){
                AttendanceReportEvent e=new AttendanceReportEvent(a.getAttendancePercentage(),a.getFacultyId(),a.getMonth(),a.getAbsent(),a.getPresent(),a.getTotalDays(),a.getLeave());
                kafkaTemplate.send("sendAttendance",e);
                return a;
            }
        }
     return new AttendanceReport();
        }
        public List<AttendanceReport> getAttendanceByPercentage(Integer percentage){
            List<AttendanceReport> ar=attendanceReportRepository.findAllByAttendancePercentage(percentage);
            List<AttendanceReport> attendanceReports=new ArrayList<>();
            for(AttendanceReport a:ar){
                if(a.getAttendancePercentage()<=percentage){
                   attendanceReports.add(a);
                }
            }
            return attendanceReports;
        }


    private String getUserName(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt=(Jwt) authentication.getPrincipal();
        return jwt.getClaim("preferred_username");
    }
    public Integer getDaysInMonth(String month){
        return switch (month) {
            case "january", "march", "may", "july", "august", "october", "december" -> 31;
            default -> 30;
        };
    }

}

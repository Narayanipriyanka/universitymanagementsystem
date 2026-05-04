package com.example.attendanceservice.servcie;

import com.example.attendanceservice.entity.AttendanceReport;
import com.example.attendanceservice.entity.FacultyDetails;
import com.example.attendanceservice.repository.AttendanceReportRepository;
import com.example.attendanceservice.repository.AttendanceRepository;
import com.example.attendanceservice.repository.FacultyDetailsRepository;
import com.example.events.LowAttendanceAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceSchedular {
    @Autowired
    private FacultyDetailsRepository facultyDetailsRepository;
    @Autowired
    private AttendanceReportRepository attendanceReportRepository;
    @Autowired
    private AttendanceService attendanceService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public AttendanceSchedular(KafkaTemplate<String,Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(cron="0 0 9 * * ?")
    public void lowAttendanceAlert(){
        int total=attendanceService.getDaysInMonth( LocalDate.now().getMonth().toString());
        List<FacultyDetails> f=facultyDetailsRepository.findAll();
        for(FacultyDetails facultyDetails:f){
            int absent=attendanceService.noOfAbsents(facultyDetails.getFacultyId(), LocalDate.now().getMonth().toString());
            int present=total-absent;
            List<AttendanceReport> ar=attendanceReportRepository.findAllByFacultyId(facultyDetails.getFacultyId());
            for(AttendanceReport a:ar){
                if(a.getMonth().equalsIgnoreCase(LocalDate.now().getMonth().toString())){
                    a.setAttendancePercentage(present*100/total);
                    attendanceReportRepository.save(a);
                }
            }
            if((present*100/total)<75){
                LowAttendanceAlert dto=new LowAttendanceAlert(facultyDetails.getEmail(),present*100/total);
                kafkaTemplate.send("lowAttendance",dto);
            }
        }
    }
}

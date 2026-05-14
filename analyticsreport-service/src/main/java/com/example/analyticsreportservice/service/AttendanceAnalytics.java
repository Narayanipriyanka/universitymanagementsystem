package com.example.analyticsreportservice.service;

import com.example.analyticsreportservice.dto.AttendanceReportDetails;
import com.example.analyticsreportservice.entity.AcademicsDetails;
import com.example.analyticsreportservice.entity.AttendanceDetails;
import com.example.analyticsreportservice.repository.AttendanceDetailsRepo;
import com.example.events.AttendanceReportEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AttendanceAnalytics {
    @Autowired
    private AttendanceDetailsRepo attendanceDetailsRepo;

    public AttendanceReportDetails getAttendnaceAnalyticsByMonth(String month){
        List<AttendanceDetails> attendanceDetails=attendanceDetailsRepo.findAllByMonth(month);
        Integer totalDays=getDaysInMonth(month);
        Integer totalFaculty=0;
        Integer totalLeastAttendanceFaculties=0;
        Integer leastAttendance=attendanceDetails.getFirst().getPresent();
        Integer highestAttendnace=attendanceDetails.getFirst().getPresent();
        Double avgAttendancePercentage=0.0;
        Double leavePercentage=0.0;
        Integer totalLeaves=0;
        Double absentPercentage=0.0;
        Integer totalAbsents=0;
        Double attendnacePercentage=0.0;
        Integer noofHighestattendnaceFaculties=0;
        for (AttendanceDetails a:attendanceDetails){
            totalFaculty++;
            totalLeaves=+a.getLeave();
            totalAbsents+=a.getAbsent();
            attendnacePercentage+=a.getAttendancePercentage();
            attendnacePercentage+=a.getAttendancePercentage();
            if(a.getPresent()<leastAttendance){
                leastAttendance=a.getPresent();
            }
            if(a.getPresent()>highestAttendnace){
                highestAttendnace=a.getPresent();
            }

        }
        for(AttendanceDetails a:attendanceDetails){
            if(a.getPresent()<=leastAttendance){
                totalLeastAttendanceFaculties++;
            }
            if(a.getPresent()>=highestAttendnace){
                noofHighestattendnaceFaculties++;
            }

        }
        leavePercentage= (double) ((totalLeaves/(totalDays*attendanceDetails.size()))*100);
       avgAttendancePercentage=attendnacePercentage/attendanceDetails.size();
       absentPercentage= (double) ((totalAbsents/(totalDays*attendanceDetails.size()))*100);
return new AttendanceReportDetails(totalFaculty,leastAttendance,totalLeastAttendanceFaculties,avgAttendancePercentage,highestAttendnace,noofHighestattendnaceFaculties,absentPercentage,leavePercentage,totalLeaves);

    }

    public List<AttendanceDetails> getAttendanceByPercentage(Integer percentage) {
        List<AttendanceDetails> ar = attendanceDetailsRepo.findAllByAttendancePercentage(percentage);
        List<AttendanceDetails> attendanceReports = new ArrayList<>();
        for (AttendanceDetails a : ar) {
            if (a.getAttendancePercentage() <= percentage) {
                attendanceReports.add(a);
            }
        }
        return attendanceReports;
    }
    public AttendanceDetails getAttendanceReportOfMonth(UUID facultyId, String month){
        List<AttendanceDetails> ar=attendanceDetailsRepo.findAllByFacultyId(facultyId);
        for(AttendanceDetails a:ar){
            if(a.getMonth().equalsIgnoreCase(month)){
                return a;
            }
        }
        return new AttendanceDetails();
    }
    public Integer getDaysInMonth(String month){
        return switch (month) {
            case "january", "march", "may", "july", "august", "october", "december" -> 31;
            default -> 30;
        };
    }
}

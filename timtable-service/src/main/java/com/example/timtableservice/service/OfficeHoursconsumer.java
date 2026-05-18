package com.example.timtableservice.service;

import com.example.events.FacultyCourseEvent;
import com.example.events.OfficeHoursEvent;
import com.example.timtableservice.entity.FacultyCourse;
import com.example.timtableservice.entity.OfficeHour;
import com.example.timtableservice.repository.FacultyCourseRepository;
import com.example.timtableservice.repository.OfficeHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OfficeHoursconsumer {
    @Autowired
    private OfficeHourRepository officeHourRepository;
    @Autowired
    private FacultyCourseRepository facultyCourseRepository;
    @KafkaListener(topics = "sendOfficeHours",groupId = "faculty-OfficeHour-group")
    public void cosume(OfficeHoursEvent officeHoursEvent){
        OfficeHour o=new OfficeHour();
        o.setFacultyId(officeHoursEvent.getFacultyId());
        o.setLiesurePeriod(officeHoursEvent.getLiesurePeriod());
        o.setLoginTime(officeHoursEvent.getLoginTime());
        o.setLogoutTime(officeHoursEvent.getLogoutTime());
        officeHourRepository.save(o);
    }
    @KafkaListener(topics = "facultyCourse",groupId = "faculty-course-group")
    public void cosumeFacultyCourse(FacultyCourseEvent dto){
        FacultyCourse f=new FacultyCourse();
        f.setCourseId(dto.getCourseId());
        f.setSemester(dto.getSemester());
        f.setCourseCode(dto.getCourseCode());
        f.setFacultyName(dto.getFacultyName());
        f.setFacultyId(dto.getFacultyId());
        f.setDeptCode(dto.getDeptCode());
        f.setProgramCode(dto.getProgramCode());
        facultyCourseRepository.save(f);
    }
}

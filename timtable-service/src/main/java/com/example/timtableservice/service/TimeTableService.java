package com.example.timtableservice.service;

import com.example.timtableservice.dto.RoomDTO;
import com.example.timtableservice.entity.ClassRoom;
import com.example.timtableservice.entity.FacultyClassRoom;
import com.example.timtableservice.entity.FacultyCourse;
import com.example.timtableservice.entity.OfficeHour;
import com.example.timtableservice.repository.ClassRoomRepository;
import com.example.timtableservice.repository.FacultyClassRoomRepository;
import com.example.timtableservice.repository.FacultyCourseRepository;
import com.example.timtableservice.repository.OfficeHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class TimeTableService {
    @Autowired
    private OfficeHourRepository officeHourRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private FacultyClassRoomRepository facultyClassRoomRepository;
    @Autowired
    private FacultyCourseRepository facultyCourseRepository;
    private final KafkaTemplate<String,Object> kafkaTemplate;

    public TimeTableService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String addClassRoom(RoomDTO dto){
        ClassRoom c=new ClassRoom();
        c.setBlockNo(dto.getBlockNo());
        c.setFloorNo(dto.getFloorNo());
        c.setRoomNo(dto.getRoomNo());
        c.setDept(dto.getDept());
        c.setProgram(dto.getProgram());
        classRoomRepository.save(c);
        return "classroom added successfully";
    }
    public String addResourcesToClassRoom(String roomNo,String resourceName,Integer count,Boolean isElectronic){
        ClassRoom r=classRoomRepository.findByRoomNo(roomNo);
        if(isElectronic){
            List<String> resources=r.getElectronicresources();
            resources.add(resourceName);
            r.setElectronicresources(resources);
           r.setNoofEResources(r.getNoofEResources()+count);
        }
        else {
            List<String> resources=r.getResources();
            resources.add(resourceName);
            r.setResources(resources);
            r.setNoOfResources(r.getNoOfResources()+count);
        }
        classRoomRepository.save(r);
        return "resources allocated succesfully";
    }
    public String classScheduling(){
        List<FacultyCourse> f=facultyCourseRepository.findAll();
        System.out.println("Faculty count = " + f.size());
        StringBuilder scheduled= new StringBuilder(" ");
        for(FacultyCourse facultyCourse:f) {
            scheduled.append(" Faculty Name:").append(facultyCourse.getFacultyName()).append(" facultyId:").append(facultyCourse.getFacultyId());
            OfficeHour officeHour = officeHourRepository.findByFacultyId(facultyCourse.getFacultyId());
            if (officeHour == null) {
                scheduled.append("\nNo office hours found");
                continue;
            }
            List<ClassRoom> c = classRoomRepository.findAllByProgramAndDept(facultyCourse.getProgramCode(), facultyCourse.getDeptCode());
            if (c.isEmpty()) {
                scheduled.append("\nNo rooms available");
                continue;
            }
            LocalTime nextPeriod = officeHour.getLoginTime();
            while (nextPeriod.isBefore(officeHour.getLogoutTime())) {
                if (nextPeriod.equals(officeHour.getLiesurePeriod())) {
                    nextPeriod = nextPeriod.plusHours(1);
                    continue;
                }
                boolean assigned = false;
                for (ClassRoom classRoom : c) {
                Boolean allocated = facultyClassRoomRepository.existsByRoomNoAndStartTime(classRoom.getRoomNo(), nextPeriod);
                    if (!allocated) {
                        FacultyClassRoom facultyClassRoom = new FacultyClassRoom();
                        facultyClassRoom.setFacultyId(facultyCourse.getFacultyId());
                        facultyClassRoom.setFacultyName(facultyCourse.getFacultyName());
                        facultyClassRoom.setCourse(facultyCourse.getCourseCode());
                        facultyClassRoom.setRoomNo(classRoom.getRoomNo());
                        facultyClassRoom.setStartTime(nextPeriod);
                        facultyClassRoom.setEndTime(nextPeriod.plusHours(1));
                        facultyClassRoomRepository.save(facultyClassRoom);
                        scheduled.append("\nRoom: " + classRoom.getRoomNo() + " Time: " + facultyClassRoom.getStartTime() + " - " + facultyClassRoom.getEndTime());
                        assigned = true;
                        break;
                    }
                }

                nextPeriod = nextPeriod.plusHours(1);

                if (assigned) {
                    break;
                }
            }
        }

        return scheduled.toString();

    }

    public String getAvailableHours(UUID facultyId){
        OfficeHour f=officeHourRepository.findByFacultyId(facultyId);
        return facultyId+"is available at "+f.getLiesurePeriod();
    }
    public String getTimeTable(String roomNo){
        List<FacultyClassRoom> facultyClassRooms=facultyClassRoomRepository.findAllByRoomNo(roomNo);
        StringBuilder timeTable= new StringBuilder("FacultyName\t\t\tcourseName\t\t\tstarttime\t\t\tendTime");
        for(FacultyClassRoom classRoom:facultyClassRooms){
            timeTable.append("\n").append(classRoom.getFacultyName()).append("\t\t\t").append(classRoom.getCourse()).append("\t\t\t").append(classRoom.getStartTime()).append("\t\t\t").append(classRoom.getEndTime());
        }

        return timeTable.toString();
    }
    public String getFacultyClassRoom(UUID facultyId){
        List<FacultyClassRoom> facultyClassRooms=facultyClassRoomRepository.findAllByFacultyId(facultyId);
        StringBuilder timeTable= new StringBuilder("Room No\t\t\tstarttime\t\t\tendTime");
        for(FacultyClassRoom classRoom:facultyClassRooms){
            timeTable.append("\n").append(classRoom.getRoomNo()).append("\t\t\t").append(classRoom.getStartTime()).append("\t\t\t").append(classRoom.getEndTime());
        }

        return timeTable.toString();
    }
}

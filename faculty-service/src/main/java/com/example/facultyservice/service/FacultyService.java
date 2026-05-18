package com.example.facultyservice.service;
import com.example.events.*;
import com.example.facultyservice.dto.FacultyProfileDTO;
import com.example.facultyservice.dto.PaySlipDTO;
import com.example.facultyservice.dto.QualificationDto;
import com.example.facultyservice.dto.ReviewDTO;
import com.example.facultyservice.entity.*;
import com.example.facultyservice.repository.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private OfficeHoursRepository officeHoursRepository;
    @Autowired
    private QualificationRepository qualificationRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PayslipRepository payslipRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    public FacultyService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
@Autowired
private CourseRepository courseRepository;
    public void registerFaculty(RegisterRequest request) {
        Faculty faculty=new Faculty();
        faculty.setFirstname(request.getFirstName());
        faculty.setLastName(request.getLastName());
        faculty.setEmail(request.getEmail());
        faculty.setGender(request.getGender());
        faculty.setPhoneNum(request.getPhoneNum());
        faculty.setUsername(request.getUsername());
        faculty.setPassword(request.getPassword());
        facultyRepository.save(faculty);
        FacultyCreatedEvent dto=new FacultyCreatedEvent(faculty.getId(),faculty.getEmail(),faculty.getUsername(),faculty.getPassword());
        kafkaTemplate.send("sendFacultyCreated",dto);
    }

    public UUID getFacultyID(String email) {
       Faculty f=facultyRepository.findByEmail(email);
       return f.getId();
    }
 public List<Faculty> addCsvOfFaculty(MultipartFile file) throws IOException {
     List<Faculty> faculty=new ArrayList<>();
     BufferedReader reader=new BufferedReader(new InputStreamReader(file.getInputStream()));
     reader.readLine();
     String line;
     while((line=reader.readLine())!=null) {
         String[] data = line.split(",");
         Faculty s = new Faculty();
         s.setFirstname(data[0]);
         s.setLastName(data[1]);
         s.setEmail(data[2]);
         s.setPhoneNum(data[3]);
         s.setGender(data[4]);
         s.setPosition(data[5]);
         s.setSalary(Double.parseDouble(data[6]));
         s.setPf(Double.parseDouble(data[7]));
         s.setJoiningDate(new Date());
         s.setUsername(data[8]);
         s.setPassword(data[9]);
         faculty.add(s);
         facultyRepository.save(s);
FacultyCreatedEvent dto=new FacultyCreatedEvent(s.getId(),data[2],data[8],data[9]);
        kafkaTemplate.send("sendFacultyCreated",dto);
     }
     return faculty;
 }
    public String createProfile(FacultyProfileDTO dto) {
        Faculty f=facultyRepository.findById(dto.getFacultyID()).orElseThrow(()->new RuntimeException("no faculty found with this id"));
     f.setPosition(dto.getPosition());
     f.setJoiningDate(dto.getJoiningDate());
     f.setSalary(dto.getFixedsalary());
     f.setPf(dto.getPf());
     f.setTax(dto.getTax());
     facultyRepository.save(f);
     return "profile created successfully for faculty "+f.getFirstname();
    }
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }
    public String addDepartment(UUID facultyId, List<String> deptCode) {
        Faculty f=facultyRepository.findById(facultyId).orElseThrow(()-> new RuntimeException("faculty ot found with this id"));
        List<Department> d=departmentRepository.findByDepartmentCodeIn(deptCode);
     if(d.isEmpty()){
         throw new RuntimeException("no departments found with this name");
     }
     f.setDepartments(d);
     facultyRepository.save(f);
        return "departments added successfully to "+f.getFirstname();
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public String addQualification(QualificationDto dto) {
        Faculty f=facultyRepository.findById(dto.getFacultyId()).orElseThrow(()->new RuntimeException("no faculty found with this id"));
        Qualification q=new Qualification();
        q.setFaculty(f);
        q.setDegree(dto.getDegree());
        q.setCertificatePath(dto.getCertificatePath());
        q.setUniversity(dto.getUniversity());
        q.setSpecializaton(dto.getSpecializaton());
        qualificationRepository.save(q);
        return "qualification added successfully to faculty"+f.getFirstname();
    }
    public String addOfficeHours(OfficeHours hours){
       OfficeHours officeHours=officeHoursRepository.findByFacultyId(hours.getFacultyId());
        if(officeHours!=null){
            throw new RuntimeException("office hour already added for this faculty");
        }
       hours.setId(null); officeHoursRepository.save(hours);
        OfficeHoursEvent dto=new OfficeHoursEvent(hours.getFacultyId(),hours.getLoginTime(),hours.getLogoutTime(),hours.getLiesurePeriod());
        kafkaTemplate.send("sendOfficeHours",dto);
        return "office hours added successfully for"+hours.getFacultyId();
    }
    public OfficeHours getOfficeHoursOfAFaculty(UUID facultyId){
        return officeHoursRepository.findByFacultyId(facultyId);

    }
    public String getAvailableHours(UUID facultyId){
        Faculty f=facultyRepository.findById(facultyId).orElseThrow(()-> new RuntimeException("no faculty found with this"));
        OfficeHours h=officeHoursRepository.findByFacultyId(facultyId);
        return f.getFirstname()+"is available at "+h.getLiesurePeriod();
    }
    public String addReview(@RequestBody ReviewDTO dto){
        Faculty f=facultyRepository.findById(dto.getFacultyId()).orElseThrow(()-> new RuntimeException("no faculty found with this"));
        Reviews r=new Reviews();
        r.setFaculty(f);
        r.setReview(dto.getReview());
        r.setDepartment(dto.getDepartment());
        r.setRating(dto.getRating());
        reviewRepository.save(r);
        return "review added successfully to "+dto.getFacultyId();
    }
    public List<Reviews> getReviewsById(UUID facultyId) {
        List<Reviews> r=reviewRepository.findAllByFacultyId(facultyId);
        return r;
    }
    public String getPerformanceOverview(UUID facultyId){
        List<Reviews> r=reviewRepository.findAllByFacultyId(facultyId);
      Integer total=0;
      for(Reviews review:r){
          total+=review.getRating();
      }
      int avgRating=total/r.size();
      if(avgRating>8){
          return "very good";
      }
      else if(avgRating>5){
          return "good";
      }
      else if(avgRating>3){
          return "need improvement";
      }
      else{
          return "poor performance";
      }
    }
   public String addPaySlip(PaySlipDTO dto){
        Faculty f=facultyRepository.findById(dto.getFacultyId()).orElseThrow(()-> new RuntimeException("no faculty found with this"));
      Payslip p=new Payslip();
      p.setFaculty(f);
      p.setMonth(dto.getMonth());
      p.setDeductions(f.getPf()+f.getTax());
      p.setEarnings(f.getSalary());
      p.setNetPay(f.getSalary()-f.getTax()+f.getPf());
      p.setTransactionId(dto.getTransactionId());
      payslipRepository.save(p);
       PayslipGeneratedEvent e=new PayslipGeneratedEvent(p.getEarnings(),p.getMonth(),p.getDeductions(),p.getNetPay(),p.getTransactionId());
      kafkaTemplate.send("sendPaySlip",e);
      return "payslip generated successfully for "+f.getFirstname()+"for"+dto.getMonth()+" month ";
   }

    public String updateProfile(FacultyProfileDTO dto) {
        Faculty f=facultyRepository.findById(dto.getFacultyID()).orElseThrow(()->new RuntimeException("no faculty found with this id"));
        f.setPosition(dto.getPosition());
        f.setJoiningDate(dto.getJoiningDate());
        f.setSalary(dto.getFixedsalary());
        facultyRepository.save(f);
        return "profile created successfully for faculty "+f.getFirstname();
    }
    public List<Course> getPendingCourses(){
        return courseRepository.findAllByStatus(CourseAllocationStatus.PENDING);
    }
    public String addCourseToFaculty(UUID facultyId,String courseCode){
        Faculty f=facultyRepository.findById(facultyId).orElseThrow(()->new RuntimeException("no faculty found with this id"));
       Course c=courseRepository.findByCourseCode(courseCode).orElseThrow(()->new RuntimeException("no course found with this id"));
       c.setFaculty(f);
       c.setStatus(CourseAllocationStatus.ALLOCATED);
       courseRepository.save(c);
       FacultyCourseEvent e=new FacultyCourseEvent(facultyId,f.getFirstname(),c.getId(),c.getProgram(),c.getSemester(),c.getDeptCode(),c.getCourseCode());
       kafkaTemplate.send("facultyCourse",e);
       return "course "+c.getCourseName()+" is allocated to "+f.getFirstname()+"successfully";
    }

}

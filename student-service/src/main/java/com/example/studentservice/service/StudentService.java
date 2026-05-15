package com.example.studentservice.service;


import com.example.events.*;
import com.example.studentservice.dto.ParentDTO;
import com.example.studentservice.dto.ProfileDTO;
import com.example.studentservice.entity.*;
import com.example.studentservice.repository.CourseRepository;
import com.example.studentservice.repository.ParentRepository;
import com.example.studentservice.repository.StudentRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private CourseRepository courseRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    public StudentService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    private static final String TOPIC = "course-enroll-topic";
public List<Student> addStudentsCsv(MultipartFile file) throws IOException {
    List<Student> students=new ArrayList<>();
    BufferedReader reader=new BufferedReader(new InputStreamReader(file.getInputStream()));
    reader.readLine();
    String line;
    while((line=reader.readLine())!=null){
        String[] data=line.split(",");
        Student s=new Student();
        s.setFirstName(data[0]);
        s.setLastName(data[1]);
        s.setEmail(data[2]);
        s.setPhoneNum(data[3]);
        s.setDepartment(data[4]);
        s.setProgram(data[5]);
        s.setCurrentYear(Integer.parseInt(data[6]));
        s.setCurrentSemester(Integer.parseInt(data[7]));
        s.setGender(data[8]);
        s.setAddress(data[9]);
        s.setUsername(data[10]);
        s.setPassword(data[11]);
        s.setStudentStatus(StudentStatus.ACTIVE);
        s.setStatus(StudentIDcardStatus.PENDING);

        students.add(s);
        StudentCreatedEvent dto=new StudentCreatedEvent(s.getId(),s.getEmail(),s.getUsername(),s.getPassword(), s.getStudentStatus().toString());
        kafkaTemplate.send("sendStudentCreated",dto);
    }
    return repository.saveAll(students);
}
public String updateStudentStatus(UUID studentId,StudentStatus status){
    Student s=repository.findById(studentId).orElseThrow(()->new RuntimeException("no student found"));
    s.setStudentStatus(status);
    repository.save(s);
    StudentCreatedEvent dto=new StudentCreatedEvent(s.getId(),s.getEmail(),s.getUsername(),s.getPassword(),s.getStudentStatus().toString());
    kafkaTemplate.send("sendStudentCreated",dto);
    return "status updated successfully";
}
    public void registerStudent(RegisterRequest req) {
        Student student = new Student();
        student.setFirstName(req.getFirstName());
        student.setLastName(req.getLastName());
        student.setUsername(req.getUsername());
        student.setPassword(req.getPassword());
        student.setEmail(req.getEmail());
        student.setPhoneNum(req.getPhoneNum());
        student.setDepartment(req.getDepartment());
        student.setGender(req.getGender());
        student.setProgram(" ");
        student.setDateOfBirth(req.getDateOfBirth());
        student.setCurrentSemester(1);
        repository.save(student);
        StudentCreatedEvent dto=new StudentCreatedEvent(student.getId(),student.getEmail(),student.getUsername(),student.getPassword(), student.getStudentStatus().toString());
        kafkaTemplate.send("sendStudentCreated",dto);

    }
    public UUID getStudentId(String email){
        Student s=repository.findByEmail(email);
        return s.getId();
    }
    public String getSyllabusOfCourse(String code){
    Course c=courseRepository.findByCourseCode(code);
    Student s=repository.findByUsername(getUserName());
        SyllabusEvent dto=new SyllabusEvent(s.getEmail(),c.getSyllabusPath(),c.getCourseCode(),s.getId());
    kafkaTemplate.send("sendSyllabusEmail",dto);
    return "the syllabus pdf is snet to your email please check ";
    }
    public String getMaterialOfCourse(String code){
        Course c=courseRepository.findByCourseCode(code);
        Student s=repository.findByUsername(getUserName());
        MaterialEvent dto=new MaterialEvent(s.getEmail(),c.getSyllabusPath(),c.getCourseCode(),s.getId());
        kafkaTemplate.send("sendMaterialEmail",dto);
        return "the material pdf is sent to your email please check ";
    }

    public Student createProfile(ProfileDTO profile){
       Student sc=repository.findById(profile.getStudentId()).orElseThrow(()->new RuntimeException("no student found with this id"));
       sc.setProgram(profile.getCourse());
       sc.setCurrentSemester(profile.getCurrentSemester());
       sc.setStudentStatus(profile.getStatus());
       sc.setCurrentYear(profile.getCurrentYear());
       return repository.save(sc);
}
    public List<Student> getstudents() {
       return repository.findAll();
    }

    public String addParent(ParentDTO parentDTO) {
        Student student=repository.findById(parentDTO.getStudentId()).orElseThrow(( )->new RuntimeException("no student found with this id"));
        Parent parent=new Parent();
        parent.setParentName(parentDTO.getParentName());
        parent.setRelation(parentDTO.getRelation());
        parent.setPhone(parentDTO.getPhone());
        parent.setStudent(student);
        parent.setEmail(parentDTO.getEmail());
        parentRepository.save(parent);
        ParentEvent dto=new ParentEvent(parent.getEmail(),parent.getStudent().getId(),parent.getParentName(),parent.getPhone(),parent.getRelation());
        kafkaTemplate.send("sendParent",dto);
        return "parent added successfully to student :"+student.getId();
    }
    public List<Course> getAllAvailableCourses(){
    return courseRepository.findAll();
    }
    public String enrollInCourse(Long courseId,String courseCode){
    Student s=repository.findByUsername(getUserName());
    Course c=courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("no course found with this id"));
    if(Objects.equals(c.getCourseCode(), courseCode)){
        List<Student> students=c.getStudents();
        students.add(s);
        c.setStudents(students);
        courseRepository.save(c);
        EnrollEvent e=new EnrollEvent(s.getId(),c.getDeptCode(),c.getId(),c.getProgram(),c.getSemester(),c.getCourseCode());
        kafkaTemplate.send("enrollCourse",e);
    }
    return "enrolled in "+courseCode+" successfully";
    }

    public List<Course> getEnrollCourses(UUID studentId) {
        List<Course> enrolled=new ArrayList<>();
    List<Course> courses=courseRepository.findAll();
    for(Course c:courses){
        for(Student s:c.getStudents()){
            if(s.getId().equals(studentId)){
                enrolled.add(c);
                break;
            }
        }
    }
    return enrolled;
    }
    private String getUserName(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt=(Jwt) authentication.getPrincipal();
        return jwt.getClaim("preferred_username");
    }
    public List<Student> uploadExcel(MultipartFile file) throws IOException {
    List<Student> students=new ArrayList<>();
    Workbook workBook= WorkbookFactory.create(file.getInputStream());
    Sheet sheet= (Sheet) workBook.getSheetAt(0);
    for(int i=1;i<=sheet.getLastRowNum();i++){
        Row row=sheet.getRow(i);
        Student s=new Student();
        s.setFirstName(row.getCell(0).getStringCellValue());
        s.setLastName(row.getCell(1).getStringCellValue());
        s.setEmail(row.getCell(2).getStringCellValue());
        s.setPhoneNum(row.getCell(3).getStringCellValue());
        s.setDepartment(row.getCell(4).getStringCellValue());
        s.setProgram(row.getCell(5).getStringCellValue());
        s.setCurrentYear((int) row.getCell(6).getNumericCellValue());
        s.setCurrentSemester((int) row.getCell(7).getNumericCellValue());
        s.setGender(row.getCell(8).getStringCellValue());
        s.setAddress(row.getCell(9).getStringCellValue());
        s.setStudentStatus(StudentStatus.ACTIVE);
        s.setStatus(StudentIDcardStatus.PENDING);
        students.add(s);
        StudentCreatedEvent dto=new StudentCreatedEvent(s.getId(),s.getEmail(),s.getUsername(),s.getPassword(), s.getStudentStatus().toString());
        kafkaTemplate.send("sendStudentCreated",dto);

    }
    workBook.close();
    return repository.saveAll(students);
    }
}
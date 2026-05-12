package com.example.examservice.service;

import com.example.examservice.dto.ExamDTO;
import com.example.examservice.entity.Exam;
import com.example.examservice.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private KafkaTemplate<String ,Object> kafkaTemplate;

    public String addExam(ExamDTO dto) {
        Exam e = new Exam();
        e.setProgramCode(dto.getProgramCode());
        e.setCourseCode(dto.getCourseCode());
        e.setExam(dto.getExam());
        e.setExamDate(dto.getExamDate());
        e.setTotalMarks(dto.getTotalMarks());
        e.setEndTime(dto.getEndTime());
        e.setStartTime(dto.getStartTime());
        e.setDeptCode(dto.getDeptCode());
        e.setSemester(dto.getSemester());
        examRepository.save(e);
        return "exam added successfully";
    }
    public String getExamSchedule(String programCode, Integer semester, String deptCode) {
        List<Exam> exams = examRepository.findAllByProgramCodeAndDeptCode(programCode, deptCode);
        StringBuilder schedule = new StringBuilder(" ");
        for (Exam exam : exams) {
            if (Objects.equals(exam.getSemester(), semester)) {
                schedule.append("Subject:").append(exam.getCourseCode()).append(" examDate:").append(exam.getExamDate()).append(" time:").append(exam.getStartTime()).append("to ").append(exam.getEndTime()).append("\n");
            }

        }
        return schedule.toString();
    }
    
}

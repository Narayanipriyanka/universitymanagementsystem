package com.example.events;

import java.util.UUID;

public class ExamFeePaidEvent {
    private UUID studentId;
    private String courseCode;
    private String programCode;
    private Integer semester;
    private Boolean feePaid;
public ExamFeePaidEvent(){

}
    public ExamFeePaidEvent(UUID studentId, String program, Integer semester, String courseCode) {
    this.studentId=studentId;
    this.courseCode=courseCode;
    this.semester=semester;
    this.programCode=program;
    this.feePaid=true;}

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getProgramCode() {
        return programCode;
    }

    public Boolean getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(Boolean feePaid) {
        this.feePaid = feePaid;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String deptCode) {
        this.courseCode = deptCode;
    }
}

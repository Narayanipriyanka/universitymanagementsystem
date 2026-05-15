package com.example.events;

import java.util.List;
import java.util.UUID;

public class DigitalResourcesEvent {
    private List<String> fileNames;
    private String courseCode;
    private List<String> filePaths;
    private String deptCode;
    private String studentUsername;
public DigitalResourcesEvent(){

}
    public DigitalResourcesEvent(List<String> filepaths, String courseCode, String userName) {
        this.filePaths=filepaths;
        this.studentUsername=userName;
        this.courseCode=courseCode;
    }

    public List<String> getFileName() {
        return fileNames;
    }

    public List<String> getFilePath() {
        return filePaths;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public void setFilePath(List<String> filePath) {
        this.filePaths = filePath;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setFileName(List<String> fileName) {
        this.fileNames = fileName;
    }
}

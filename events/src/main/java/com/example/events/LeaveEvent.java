package com.example.events;

import java.util.UUID;

public class LeaveEvent {
    private UUID facultyId;
    private String email;
    private String status;

    public LeaveEvent(UUID facultyId, String email, String leaveStatus) {
        this.email=email;
        this.facultyId=facultyId;
        this.status=leaveStatus;
    }

    public UUID getFacultyId() {
        return facultyId;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFacultyId(UUID facultyId) {
        this.facultyId = facultyId;
    }
}

package com.example.timtableservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalTime;
import java.util.UUID;

@Entity
public class OfficeHour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID facultyId;
    private LocalTime loginTime;
    private LocalTime logoutTime;
    private LocalTime liesurePeriod;
    public UUID getFacultyId() {
        return facultyId;
    }

    public LocalTime getLiesurePeriod() {
        return liesurePeriod;
    }

    public void setLiesurePeriod(LocalTime liesurePeriod) {
        this.liesurePeriod = liesurePeriod;
    }

    public LocalTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public LocalTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalTime loginTime) {
        this.loginTime = loginTime;
    }

    public void setFacultyId(UUID facultyId) {
        this.facultyId = facultyId;
    }
}

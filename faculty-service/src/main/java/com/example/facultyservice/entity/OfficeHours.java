package com.example.facultyservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalTime;
import java.util.UUID;

@Entity
public class OfficeHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID facultyId;
    @Schema(type = "string", example = "09:00:00")
    private LocalTime loginTime;
    @Schema(type = "string", example = "17:00:00")
    private LocalTime logoutTime;
    @Schema(type = "string", example = "11:00:00")
    private LocalTime liesurePeriod;
    public LocalTime getLiesurePeriod() {
        return liesurePeriod;
    }

    public void setLiesurePeriod(LocalTime liesurePeriod) {
        this.liesurePeriod = liesurePeriod;
    }

    public UUID getFacultyId() {
        return facultyId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

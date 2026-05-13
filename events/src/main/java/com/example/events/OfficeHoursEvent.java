package com.example.events;

import java.time.LocalTime;
import java.util.UUID;

public class OfficeHoursEvent {
    private UUID facultyId;
    private LocalTime loginTime;
    private LocalTime logoutTime;
    private LocalTime liesurePeriod;
    public OfficeHoursEvent(UUID facultyId, LocalTime loginTime, LocalTime logoutTime, LocalTime liesurePeriod) {
    this.facultyId=facultyId;
    this.loginTime=loginTime;
    this.logoutTime=logoutTime;
    this.liesurePeriod=liesurePeriod;}

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

package com.example.events;

public class LowAttendanceAlert {
    private String email;
    private int percentage;

    public LowAttendanceAlert(String email, int percentage) {
        this.email=email;
        this.percentage=percentage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}

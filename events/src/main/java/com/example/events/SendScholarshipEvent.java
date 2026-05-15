package com.example.events;

public class SendScholarshipEvent {
    private String email;
    private String status;

    public SendScholarshipEvent(String email, String status) {
    this.email=email;
    this.status=status;
    }
public SendScholarshipEvent(){

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
}

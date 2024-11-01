package com.example.emailsender.resources;

public class EmailMessage {

    private String  dist;
    private String subject;
    private String message;

    public EmailMessage() {
    }
    public EmailMessage(String to, String subject, String message) {
        this.dist = to;
        this.subject = subject;
        this.message = message;
    }

    public void setDist(String to) {
        this.dist = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDist() {
        return dist;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}

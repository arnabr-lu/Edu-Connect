package com.example.educonnect;


public class ApplicationData {

    private String subject;
    private String body;
    private String to;
    private String from;
    private String attachment;

    public ApplicationData(String subject, String body, String to, String from, String attachment) {
        this.subject = subject;
        this.body = body;
        this.to = to;
        this.from = from;
        this.attachment = attachment;
    }

    public ApplicationData() {

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
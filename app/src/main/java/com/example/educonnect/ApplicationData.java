package com.example.educonnect;


public class ApplicationData {

    private String title;
    private String body;
    private String to;
    private String from;
    private String attachment;

    public ApplicationData(String title, String body, String to, String from, String attachment) {
        this.title = title;
        this.body = body;
        this.to = to;
        this.from = from;
        this.attachment = attachment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

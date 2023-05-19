package com.example.demo.Controllers.FolderActions;

public class FilterParameters {
    private String subject;
    private String sender;
    private int option;
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public int getOption() {
        return option;
    }
    public void setOption(int option) {
        this.option = option;
    }
    @Override
    public String toString() {
        return "FilterParameters [subject=" + subject + ", sender=" + sender + ", option=" + option + "]";
    }
}

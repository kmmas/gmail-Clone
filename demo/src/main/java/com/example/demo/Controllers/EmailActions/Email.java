package com.example.demo.Controllers.EmailActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Email {
    private int id;
    private String sender;
    private List<String> recievers = new ArrayList<>();
    private String subject;
    private String body;
    private int priority;
    private Date date;
    private List<String> attachmentsList = new ArrayList<>();

    public Email(){

    }
    
    public Email(int id, String sender, List<String> recievers, String subject, String body, int priority, Date date,
            List<String> attachmentsList) {
        this.id = id;
        this.sender = sender;
        this.recievers = recievers;
        this.subject = subject;
        this.body = body;
        this.priority = priority;
        this.date = date;
        this.attachmentsList = attachmentsList;
    }
    public List<String> getAttachmentsList() {
        return attachmentsList;
    }
    public void setAttachmentsList(List<String> attachmentsList) {
        this.attachmentsList = attachmentsList;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public List<String> getRecievers() {
        return recievers;
    }
    public void setRecievers(List<String> recievers) {
        this.recievers = recievers;
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
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "Email [id=" + id + ", sender=" + sender + ", recievers=" + recievers + ", subject=" + subject
                + ", body=" + body + ", priority=" + priority + ", date=" + date + ", attachmentsList="
                + attachmentsList + "]";
    }

    public Email clone(){
        return new Email(id,sender,new ArrayList<String>(recievers),subject,body,priority,date,attachmentsList);
    }

    public Email clone(String...reciever){
        return new Email(id,sender,new ArrayList<String>(Arrays.asList(reciever)),subject,body,priority,date,attachmentsList);
    }
   
}

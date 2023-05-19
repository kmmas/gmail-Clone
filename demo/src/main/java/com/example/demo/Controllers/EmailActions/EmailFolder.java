package com.example.demo.Controllers.EmailActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailFolder {
    private String name;
    private int newid = 0;
    private Map<Integer, Email> emailsList = new HashMap<>();

    public EmailFolder() {

    }

    public EmailFolder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNewid() {
        return newid;
    }

    public void setNewid(int newid) {
        this.newid = newid;
    }

    public Map<Integer, Email> getEmailsList() {
        return emailsList;
    }

    public void setEmailsList(Map<Integer, Email> emailsList) {
        this.emailsList = emailsList;
    }

    public void AddEmail(Email email) {
        email.setId(newid);
        emailsList.put(newid, email);
        newid++;
    }

    public void RemoveEmail(int id) {
        emailsList.remove(id);
    }

    public Email GetEmail(int id) {
        return emailsList.get(id);
    }

    public List<Email> GetAllEmails(){
        return new ArrayList<Email>(emailsList.values());
    }
}

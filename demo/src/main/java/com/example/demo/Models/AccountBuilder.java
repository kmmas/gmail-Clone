package com.example.demo.Models;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Controllers.EmailActions.EmailFolder;

public class AccountBuilder {
    private String username;
    private String address;
    private String password;
    private List<EmailFolder> folders = new ArrayList<>();
    private List<Contact> contacts = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<EmailFolder> getFolders() {
        return folders;
    }

    public void setFolders(List<EmailFolder> folders) {
        this.folders = folders;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public AccountBuilder(String username,String address,String password){
        this.username = username;
        this.address = address;
        this.password = password;
    }

    public AccountBuilder SetDefaultFolders(){
        folders.add(new EmailFolder("inbox"));
        folders.add(new EmailFolder("sent"));
        folders.add(new EmailFolder("trash"));
        folders.add(new EmailFolder("draft"));
        return this;
    }

    public Account Build(){
        return new Account(this);
    }
}

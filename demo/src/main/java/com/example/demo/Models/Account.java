package com.example.demo.Models;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Controllers.EmailActions.EmailFolder;

public class Account {
    private String username;
    private String address;
    private String password;
    private List<EmailFolder> folders = new ArrayList<>();
    private List<Contact> contacts = new ArrayList<>();

    public Account() {

    }

    public Account(AccountBuilder account) {
        this.username = account.getUsername();
        this.address = account.getAddress();
        this.password = account.getPassword();
        this.folders = account.getFolders();
    }

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

    public EmailFolder GetFolderByName(String foldername) {
        for (EmailFolder Efolder : folders) {
            if (Efolder.getName().equals(foldername)) {
                return Efolder;
            }
        }
        return null;
    }

    public boolean FolderNameTaken(String foldername) {
        for (EmailFolder Efolder : folders) {
            if (Efolder.getName().equals(foldername)) {
                return true;
            }
        }
        return false;
    }

    public void RemoveFolderByName(String foldername) {
        int size = folders.size();
        for (int i = 0; i < size; i++) {
            if (folders.get(i).getName().equals(foldername)) {
                folders.remove(i);
                return;
            }
        }
    }

    public void CreateNewFolder(String foldername) {
        folders.add(new EmailFolder(foldername));
    }

    public void RenameFolder(String oldfoldername, String newfoldername) {
        GetFolderByName(oldfoldername).setName(newfoldername);
    }

    public List<String> GetUserMadeFolders() {
        List<String> FoldersNames = new ArrayList<>();
        for (int i = 4; i < folders.size(); i++) {
            FoldersNames.add(folders.get(i).getName());
        }
        return FoldersNames;
    }

    public Contact GetContactByName(String name){
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)){
                return contact;
            }
        }
        return null;
    }

    public boolean ContactNameTaken(String name){
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void CreateNewContact(String name){
        contacts.add(new Contact(name));
    }

    public void RemoveContactByName(String name){
        int size = contacts.size();
        for (int i = 0; i < size; i++) {
            if (contacts.get(i).getName().equals(name)){
                contacts.remove(i);
                return;
            }
        }
    }

    public void RenameContact(String oldname ,String newname){
        GetContactByName(oldname).setName(newname);
    }
}

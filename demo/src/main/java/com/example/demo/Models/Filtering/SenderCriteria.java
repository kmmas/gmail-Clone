package com.example.demo.Models.Filtering;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Controllers.EmailActions.Email;

public class SenderCriteria implements Criteria{

    private String Sender;

    public SenderCriteria(String sender) {
        Sender = sender;
    }

    @Override
    public List<Email> MeetCriteria(List<Email> emails) {
        List<Email> EmailList = new ArrayList<>();
        for (Email email : emails) {
            if (email.getSender().equals(Sender)){
                EmailList.add(email);
            }
        }
        return EmailList;
    }
    
}

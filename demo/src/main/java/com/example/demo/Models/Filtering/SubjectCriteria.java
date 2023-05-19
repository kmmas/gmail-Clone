package com.example.demo.Models.Filtering;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Controllers.EmailActions.Email;

public class SubjectCriteria implements Criteria{

    private String Subject;

    public SubjectCriteria(String subject) {
        Subject = subject;
    }

    @Override
    public List<Email> MeetCriteria(List<Email> emails) {
        List<Email> EmailList = new ArrayList<>();
        for (Email email : emails) {
            if (email.getSubject().equals(Subject)){
                EmailList.add(email);
            }
        }
        return EmailList;
    }
    
}

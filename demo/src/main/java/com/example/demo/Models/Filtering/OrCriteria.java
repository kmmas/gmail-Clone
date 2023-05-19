package com.example.demo.Models.Filtering;

import java.util.List;

import com.example.demo.Controllers.EmailActions.Email;

public class OrCriteria implements Criteria{

    private Criteria criteria1;
    private Criteria criteria2;

    public OrCriteria(Criteria criteria1, Criteria criteria2) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }

    @Override
    public List<Email> MeetCriteria(List<Email> emails) {
        List<Email> list1 = criteria1.MeetCriteria(emails);
        List<Email> list2 = criteria2.MeetCriteria(emails);
        for (Email email : list1) {
            if (!list2.contains(email)){
                list2.add(email);
            }
        }
        return list2;
    }
    
}

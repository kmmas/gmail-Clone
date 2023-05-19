package com.example.demo.Models.Filtering;

import java.util.List;

import com.example.demo.Controllers.EmailActions.Email;

public class AndCriteria implements Criteria {

    private Criteria criteria1;
    private Criteria criteria2;

    public AndCriteria(Criteria criteria1, Criteria criteria2) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }

    @Override
    public List<Email> MeetCriteria(List<Email> emails) {
        return criteria2.MeetCriteria(criteria1.MeetCriteria(emails));
    }

}

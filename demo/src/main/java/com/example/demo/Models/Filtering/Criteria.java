package com.example.demo.Models.Filtering;

import java.util.List;

import com.example.demo.Controllers.EmailActions.Email;

public interface Criteria {
    public List<Email> MeetCriteria(List<Email> emails);
}

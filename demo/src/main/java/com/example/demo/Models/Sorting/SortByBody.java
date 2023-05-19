package com.example.demo.Models.Sorting;

import java.util.Comparator;

import com.example.demo.Controllers.EmailActions.Email;

public class SortByBody implements Comparator<Email> {

    @Override
    public int compare(Email arg0, Email arg1) {
        return arg0.getBody().compareTo(arg1.getBody());
    }

}

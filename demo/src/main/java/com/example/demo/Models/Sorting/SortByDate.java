package com.example.demo.Models.Sorting;

import java.util.Comparator;

import com.example.demo.Controllers.EmailActions.Email;

public class SortByDate implements Comparator<Email>{

    @Override
    public int compare(Email arg0, Email arg1) {
        return arg1.getDate().compareTo(arg0.getDate());
    }
    
}

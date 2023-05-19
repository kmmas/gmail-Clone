package com.example.demo.Controllers.authentication;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.Database;

@RestController
@CrossOrigin("http://localhost:4200/")
public class authenticationController {
    @PostMapping("/signin")
    public int Signin(@RequestBody signinparameter info) {
        return Database.getInstance().Signin(info.email, info.password);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody signupparameter info) {
        return Database.getInstance().AddAccount(info.username, info.email, info.password);
    }
}

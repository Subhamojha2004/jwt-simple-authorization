package com.example.jwt_auth_with_authorisation.controller;

import com.example.jwt_auth_with_authorisation.entity.MyUser;
import com.example.jwt_auth_with_authorisation.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private MyService service;

    @PostMapping("/api/register")
    public ResponseEntity<?> registerUser(@RequestBody MyUser myUser)
    {
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        System.out.println("received the post request");
        MyUser myUser1 = service.registerUser(myUser);
        return ResponseEntity.ok(myUser1);
    }

    @GetMapping("/security/get")
    public ResponseEntity<?> chekAuth()
    {
        return ResponseEntity.ok("success");
    }


}

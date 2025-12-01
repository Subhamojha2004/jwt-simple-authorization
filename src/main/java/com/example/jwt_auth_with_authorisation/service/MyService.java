package com.example.jwt_auth_with_authorisation.service;

import com.example.jwt_auth_with_authorisation.entity.MyUser;
import com.example.jwt_auth_with_authorisation.repository.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    @Autowired
    private MyUserRepo repo;

    public MyUser registerUser(MyUser myUser)
    {
        return repo.save(myUser);
    }
}

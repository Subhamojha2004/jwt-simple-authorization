package com.example.jwt_auth_with_authorisation.security;

import com.example.jwt_auth_with_authorisation.repository.MyUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private MyUserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //need repository
        //should return UserDetials object by implementing my user from UserDetails

        return repo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user not found"));
    }
}

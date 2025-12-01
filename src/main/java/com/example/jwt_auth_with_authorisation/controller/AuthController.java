package com.example.jwt_auth_with_authorisation.controller;

import com.example.jwt_auth_with_authorisation.JWTUtil.JWTUtil;
import com.example.jwt_auth_with_authorisation.entity.AuthRequest;
import com.example.jwt_auth_with_authorisation.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/api/login")
    public ResponseEntity<?> check(@RequestBody AuthRequest authRequest)
    {
        //recived the username and the password , but put it in UsernamePasswordAuthenticationToken by using Authentication manager as in jwt auth spring doesnot create its own
        try
        {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));

            //if the authentication is correct then only we have to generate teh token  else exception will raise

            String s = jwtUtil.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(s);
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}

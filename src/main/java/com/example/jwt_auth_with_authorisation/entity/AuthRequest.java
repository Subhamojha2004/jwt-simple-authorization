package com.example.jwt_auth_with_authorisation.entity;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}

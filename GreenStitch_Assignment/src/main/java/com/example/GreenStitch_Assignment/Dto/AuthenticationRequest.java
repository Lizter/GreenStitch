package com.example.GreenStitch_Assignment.Dto;

import org.springframework.security.core.Authentication;

public class AuthenticationRequest {
    private String username;
    private String password;

    // Constructors, getters, and setters

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters

    public Authentication getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


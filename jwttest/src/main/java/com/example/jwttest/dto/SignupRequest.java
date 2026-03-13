package com.example.jwttest.dto;

public record SignupRequest (String firstName,
                             String lastName,
                             String email,
                             String password){}

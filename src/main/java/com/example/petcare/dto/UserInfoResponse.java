package com.example.petcare.dto;

public record UserInfoResponse(
        String login,
        String name,
        String surname,
        String role
) {}
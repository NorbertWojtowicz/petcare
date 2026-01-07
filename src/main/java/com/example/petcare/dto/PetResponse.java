package com.example.petcare.dto;

import java.time.LocalDate;

public record PetResponse(
        Integer id,
        String name,
        String species,
        String breed,
        LocalDate dateOfBirth,
        String ownerLogin
) {}
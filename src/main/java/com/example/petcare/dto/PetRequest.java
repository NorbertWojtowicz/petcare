package com.example.petcare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public record PetRequest(
        @NotBlank(message = "Imię zwierzaka jest wymagane")
        String name,

        @NotBlank(message = "Gatunek jest wymagany")
        String species,

        String breed,

        @PastOrPresent(message = "Data urodzenia nie może być z przyszłości")
        LocalDate dateOfBirth,

        @NotNull(message = "Waga jest wymagana")
        Double weight
) {}
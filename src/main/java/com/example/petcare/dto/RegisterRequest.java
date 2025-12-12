package com.example.petcare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterRequest(

        @NotBlank(message = "Login nie może być pusty")
        String login,

        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$",
                message = "Hasło musi mieć min. 8 znaków, zawierać 1 literę i 1 cyfrę")
        String password,

        @NotBlank(message = "Imię jest wymagane")
        String name,

        @NotBlank(message = "Nazwisko jest wymagane")
        String surname
) {}
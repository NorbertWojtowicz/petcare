package com.example.petcare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class PetCareController {

    @GetMapping
    public String bookVisit() {
        return "Cześć! Jesteś zalogowany i możesz umówić wizytę!";
    }
}
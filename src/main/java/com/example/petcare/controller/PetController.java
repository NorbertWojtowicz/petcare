package com.example.petcare.controller;

import com.example.petcare.dto.PetRequest;
import com.example.petcare.dto.PetResponse;
import com.example.petcare.service.PetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping
    public ResponseEntity<String> addPet(@Valid @RequestBody PetRequest request) {
        petService.addPet(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Zwierzak został dodany pomyślnie!");
    }

    @GetMapping("/species/{species}")
    public ResponseEntity<List<PetResponse>> getPetsBySpecies(@PathVariable String species) {
        List<PetResponse> pets = petService.getPetsBySpecies(species);
        return ResponseEntity.ok(pets);
    }
}
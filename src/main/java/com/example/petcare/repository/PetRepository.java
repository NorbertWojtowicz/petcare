package com.example.petcare.repository;

import com.example.petcare.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findByOwnerLogin(String login);

    List<Pet> findAllBySpeciesIgnoreCase(String species);
}
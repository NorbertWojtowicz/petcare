package com.example.petcare.service;

import com.example.petcare.dto.PetRequest;
import com.example.petcare.model.Owner;
import com.example.petcare.model.Pet;
import com.example.petcare.model.PetDetails;
import com.example.petcare.repository.OwnerRepository;
import com.example.petcare.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.petcare.dto.PetResponse;
import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    @Transactional
    public void addPet(PetRequest request) {
        String currentOwnerLogin = SecurityContextHolder.getContext().getAuthentication().getName();

        Owner owner = ownerRepository.findByLogin(currentOwnerLogin)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono właściciela"));

        PetDetails details = new PetDetails();
        details.setName(request.name());
        details.setDateOfBirth(request.dateOfBirth());
        details.setWeight(request.weight());
        details.setTypeId(2);

        Pet pet = new Pet();
        pet.setName(request.name());
        pet.setSpecies(request.species());
        pet.setBreed(request.breed());
        pet.setDateOfBirth(request.dateOfBirth());

        pet.setOwner(owner);
        pet.setDetails(details);

        petRepository.save(pet);
    }

    public List<PetResponse> getPetsBySpecies(String species) {
        return petRepository.findAllBySpeciesIgnoreCase(species)
                .stream()
                .map(pet -> new PetResponse(
                        pet.getId(),
                        pet.getName(),
                        pet.getSpecies(),
                        pet.getBreed(),
                        pet.getDateOfBirth(),
                        pet.getOwner().getLogin()
                ))
                .collect(Collectors.toList());
    }
}
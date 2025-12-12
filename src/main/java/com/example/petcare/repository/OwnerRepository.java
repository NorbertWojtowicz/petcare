package com.example.petcare.repository;

import com.example.petcare.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    Optional<Owner> findByLogin(String login);
    boolean existsByLogin(String login);
}
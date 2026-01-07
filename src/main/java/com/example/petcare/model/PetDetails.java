package com.example.petcare.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "pets_details")
@Data
public class PetDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_details_id")
    private Integer id;

    @Column(name = "pet_name", nullable = false)
    private String name;

    @Column(name = "pet_date_birth")
    private LocalDate dateOfBirth;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "pet_type_id")
    private Integer typeId;
}

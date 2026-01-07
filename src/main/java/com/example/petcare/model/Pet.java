package com.example.petcare.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "pets")
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Integer id;

    @Column(name = "pet_name", nullable = false)
    private String name;

    @Column(name = "pet_species", nullable = false)
    private String species;

    @Column(name = "pet_breed")
    private String breed;

    @Column(name = "pet_date_of_birth")
    private LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_details_id", referencedColumnName = "pet_details_id", nullable = false)
    private PetDetails details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;
}

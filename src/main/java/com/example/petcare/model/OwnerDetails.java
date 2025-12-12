package com.example.petcare.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "owner_details")
@Data
public class OwnerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_details_id")
    private Integer id;

    @Column(name = "owner_name", nullable = false)
    private String name;

    @Column(name = "owner_surname", nullable = false)
    private String surname;
}
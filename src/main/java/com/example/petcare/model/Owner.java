package com.example.petcare.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "owners")
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Integer id;

    @Column(name = "owner_login", nullable = false, unique = true)
    private String login;

    @Column(name = "owner_password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id_details", referencedColumnName = "owner_details_id")
    private OwnerDetails details;

    @CreationTimestamp
    @Column(name = "owner_created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "owner_updated_at")
    private LocalDateTime updatedAt;
}
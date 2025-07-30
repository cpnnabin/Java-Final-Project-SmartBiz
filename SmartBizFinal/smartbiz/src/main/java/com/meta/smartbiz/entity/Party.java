package com.meta.smartbiz.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parties")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;
    private String phone;
    private String address;
    private String gstNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PartyType partyType;

    @Column(nullable = false)
    private Double openingBalance = 0.0;

    @Column(nullable = false)
    private Double currentBalance = 0.0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}


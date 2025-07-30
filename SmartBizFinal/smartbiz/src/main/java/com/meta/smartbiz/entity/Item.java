package com.meta.smartbiz.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;
    private String unit;
    private String hsn;

    @Column(nullable = false)
    private Double salePrice = 0.0;

    @Column(nullable = false)
    private Double purchasePrice = 0.0;

    @Column(nullable = false)
    private Double currentStock = 0.0;

    @Column(nullable = false)
    private Double minStock = 0.0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}


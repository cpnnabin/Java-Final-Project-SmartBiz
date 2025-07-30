package com.meta.smartbiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchase_items")
public class PurchaseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private Double rate;

    @Column(nullable = false)
    private Double amount;

    private Double taxRate = 0.0;
    private Double taxAmount = 0.0;
}


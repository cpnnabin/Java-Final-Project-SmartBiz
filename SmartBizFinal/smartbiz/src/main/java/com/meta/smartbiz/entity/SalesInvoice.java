package com.meta.smartbiz.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales_invoices")
public class SalesInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String invoiceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    @Column(nullable = false)
    private LocalDate invoiceDate;

    private LocalDate dueDate;

    @Column(nullable = false)
    private Double subtotal = 0.0;

    @Column(nullable = false)
    private Double taxAmount = 0.0;

    @Column(nullable = false)
    private Double totalAmount = 0.0;

    @Column(nullable = false)
    private Double paidAmount = 0.0;

    @Column(nullable = false)
    private Double balanceAmount = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvoiceStatus status = InvoiceStatus.PENDING;

    @OneToMany(mappedBy = "salesInvoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SalesInvoiceItem> items;

    private String notes;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}


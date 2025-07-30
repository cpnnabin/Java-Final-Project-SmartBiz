package com.meta.smartbiz.dto;

import com.meta.smartbiz.entity.PurchaseStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDto {
    private Long id;
    private String purchaseNumber;
    private Long partyId;
    private String partyName;
    private LocalDate purchaseDate;
    private LocalDate dueDate;
    private Double subtotal;
    private Double taxAmount;
    private Double totalAmount;
    private Double paidAmount;
    private Double balanceAmount;
    private PurchaseStatus status;
    private String notes;
    private List<PurchaseItemDto> items;
}


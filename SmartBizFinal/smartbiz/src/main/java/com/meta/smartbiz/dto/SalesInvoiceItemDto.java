package com.meta.smartbiz.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesInvoiceItemDto {
    private Long id;
    private Long itemId;
    private String itemName;
    private Double quantity;
    private Double rate;
    private Double amount;
    private Double taxRate;
    private Double taxAmount;
}


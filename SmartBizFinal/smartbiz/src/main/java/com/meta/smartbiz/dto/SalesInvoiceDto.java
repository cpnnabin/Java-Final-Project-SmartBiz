package com.meta.smartbiz.dto;

import com.meta.smartbiz.entity.InvoiceStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesInvoiceDto {
    private Long id;
    private String invoiceNumber;
    private Long partyId;
    private String partyName;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private Double subtotal;
    private Double taxAmount;
    private Double totalAmount;
    private Double paidAmount;
    private Double balanceAmount;
    private InvoiceStatus status;
    private String notes;
    private List<SalesInvoiceItemDto> items;
}


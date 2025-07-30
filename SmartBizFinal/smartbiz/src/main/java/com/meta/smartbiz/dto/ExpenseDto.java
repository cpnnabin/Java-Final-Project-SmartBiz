package com.meta.smartbiz.dto;

import com.meta.smartbiz.entity.ExpenseCategory;
import com.meta.smartbiz.entity.PaymentMethod;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    private Long id;
    private String description;
    private Double amount;
    private LocalDate expenseDate;
    private ExpenseCategory category;
    private PaymentMethod paymentMethod;
    private String reference;
    private String notes;
}


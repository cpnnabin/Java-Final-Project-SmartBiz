package com.meta.smartbiz.dto;

import com.meta.smartbiz.entity.PaymentMethod;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInDto {
    private Long id;
    private Long partyId;
    private String partyName;
    private Double amount;
    private LocalDate paymentDate;
    private PaymentMethod paymentMethod;
    private String reference;
    private String notes;
}


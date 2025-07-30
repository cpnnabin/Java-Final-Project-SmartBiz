package com.meta.smartbiz.dto;

import com.meta.smartbiz.entity.PartyType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String gstNumber;
    private PartyType partyType;
    private Double openingBalance;
    private Double currentBalance;
}


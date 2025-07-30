package com.meta.smartbiz.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private String unit;
    private String hsn;
    private Double salePrice;
    private Double purchasePrice;
    private Double currentStock;
    private Double minStock;
}


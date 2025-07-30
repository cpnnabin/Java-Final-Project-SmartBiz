package com.meta.smartbiz.mapper;

import com.meta.smartbiz.dto.ItemDto;
import com.meta.smartbiz.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDto toDto(Item item) {
        if (item == null) return null;
        
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setUnit(item.getUnit());
        dto.setHsn(item.getHsn());
        dto.setSalePrice(item.getSalePrice());
        dto.setPurchasePrice(item.getPurchasePrice());
        dto.setCurrentStock(item.getCurrentStock());
        dto.setMinStock(item.getMinStock());
        return dto;
    }

    public Item toEntity(ItemDto dto) {
        if (dto == null) return null;
        
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setUnit(dto.getUnit());
        item.setHsn(dto.getHsn());
        item.setSalePrice(dto.getSalePrice() != null ? dto.getSalePrice() : 0.0);
        item.setPurchasePrice(dto.getPurchasePrice() != null ? dto.getPurchasePrice() : 0.0);
        item.setCurrentStock(dto.getCurrentStock() != null ? dto.getCurrentStock() : 0.0);
        item.setMinStock(dto.getMinStock() != null ? dto.getMinStock() : 0.0);
        return item;
    }
}


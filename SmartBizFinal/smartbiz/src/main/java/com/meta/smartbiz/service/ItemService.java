package com.meta.smartbiz.service;

import com.meta.smartbiz.dto.ItemDto;
import com.meta.smartbiz.entity.Item;
import com.meta.smartbiz.mapper.ItemMapper;
import com.meta.smartbiz.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    public List<ItemDto> getAllItems() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ItemDto> getItemById(Long id) {
        return itemRepository.findById(id)
                .map(itemMapper::toDto);
    }

    public ItemDto saveItem(ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);
        Item savedItem = itemRepository.save(item);
        return itemMapper.toDto(savedItem);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public List<ItemDto> searchItems(String name) {
        return itemRepository.findByNameContainingIgnoreCase(name).stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ItemDto> getLowStockItems() {
        return itemRepository.findLowStockItems().stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }
}


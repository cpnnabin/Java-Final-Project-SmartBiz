package com.meta.smartbiz.repo;

import com.meta.smartbiz.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT i FROM Item i WHERE i.currentStock <= i.minStock")
    List<Item> findLowStockItems();
}


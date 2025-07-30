package com.meta.smartbiz.repo;

import com.meta.smartbiz.entity.Purchase;
import com.meta.smartbiz.entity.PurchaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByPartyId(Long partyId);
    List<Purchase> findByStatus(PurchaseStatus status);
    List<Purchase> findByPurchaseDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT MAX(p.purchaseNumber) FROM Purchase p")
    String findLastPurchaseNumber();
}


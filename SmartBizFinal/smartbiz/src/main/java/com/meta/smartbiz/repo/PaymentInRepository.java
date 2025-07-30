package com.meta.smartbiz.repo;

import com.meta.smartbiz.entity.PaymentIn;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PaymentInRepository extends JpaRepository<PaymentIn, Long> {
    List<PaymentIn> findByPartyId(Long partyId);
    List<PaymentIn> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
}


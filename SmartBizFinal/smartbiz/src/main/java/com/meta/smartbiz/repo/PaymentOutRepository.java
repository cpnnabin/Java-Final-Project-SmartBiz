package com.meta.smartbiz.repo;

import com.meta.smartbiz.entity.PaymentOut;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PaymentOutRepository extends JpaRepository<PaymentOut, Long> {
    List<PaymentOut> findByPartyId(Long partyId);
    List<PaymentOut> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
}


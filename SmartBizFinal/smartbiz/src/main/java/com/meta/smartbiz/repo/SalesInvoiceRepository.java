package com.meta.smartbiz.repo;

import com.meta.smartbiz.entity.SalesInvoice;
import com.meta.smartbiz.entity.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Long> {
    List<SalesInvoice> findByPartyId(Long partyId);
    List<SalesInvoice> findByStatus(InvoiceStatus status);
    List<SalesInvoice> findByInvoiceDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT MAX(s.invoiceNumber) FROM SalesInvoice s")
    String findLastInvoiceNumber();
}


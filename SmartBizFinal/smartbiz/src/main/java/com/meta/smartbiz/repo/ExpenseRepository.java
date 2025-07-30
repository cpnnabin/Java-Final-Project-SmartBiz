package com.meta.smartbiz.repo;

import com.meta.smartbiz.entity.Expense;
import com.meta.smartbiz.entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByExpenseDateBetween(LocalDate startDate, LocalDate endDate);
    List<Expense> findByCategory(ExpenseCategory category);
}


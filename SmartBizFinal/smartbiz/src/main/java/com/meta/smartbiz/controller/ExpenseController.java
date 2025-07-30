package com.meta.smartbiz.controller;

import com.meta.smartbiz.entity.ExpenseCategory;
import com.meta.smartbiz.entity.PaymentMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @GetMapping
    public String expensesList(Model model) {
        // Add expenses list here when implemented
        return "expenses";
    }

    @GetMapping("/new")
    public String newExpense(Model model) {
        model.addAttribute("categories", ExpenseCategory.values());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        return "expenses/form";
    }
}


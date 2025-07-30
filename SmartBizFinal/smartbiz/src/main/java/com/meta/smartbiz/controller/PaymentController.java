package com.meta.smartbiz.controller;

import com.meta.smartbiz.entity.PaymentMethod;
import com.meta.smartbiz.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    @Autowired
    private PartyService partyService;

    @GetMapping("/payment-in")
    public String paymentInList(Model model) {
        // Add payment in list here when implemented
        return "payment-in";
    }

    @GetMapping("/payment-in/new")
    public String newPaymentIn(Model model) {
        model.addAttribute("customers", partyService.getCustomers());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        return "payment-in/form";
    }

    @GetMapping("/payment-out")
    public String paymentOutList(Model model) {
        // Add payment out list here when implemented
        return "payment-out";
    }

    @GetMapping("/payment-out/new")
    public String newPaymentOut(Model model) {
        model.addAttribute("suppliers", partyService.getSuppliers());
        model.addAttribute("paymentMethods", PaymentMethod.values());
        return "payment-out/form";
    }
}


package com.meta.smartbiz.controller;

import com.meta.smartbiz.service.PartyService;
import com.meta.smartbiz.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private PartyService partyService;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public String salesList(Model model) {
        // Add sales invoices list here when implemented
        return "sales";
    }

    @GetMapping("/new")
    public String newSalesInvoice(Model model) {
        model.addAttribute("customers", partyService.getCustomers());
        model.addAttribute("items", itemService.getAllItems());
        return "sales/form";
    }
}


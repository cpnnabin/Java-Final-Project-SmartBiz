package com.meta.smartbiz.controller;

import com.meta.smartbiz.service.PartyService;
import com.meta.smartbiz.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PartyService partyService;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public String purchaseList(Model model) {
        // Add purchase list here when implemented
        return "purchase";
    }

    @GetMapping("/new")
    public String newPurchase(Model model) {
        model.addAttribute("suppliers", partyService.getSuppliers());
        model.addAttribute("items", itemService.getAllItems());
        return "purchase/form";
    }
}


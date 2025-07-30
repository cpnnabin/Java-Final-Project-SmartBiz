package com.meta.smartbiz.controller;

import com.meta.smartbiz.service.ItemService;
import com.meta.smartbiz.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private PartyService partyService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Add dashboard statistics
        model.addAttribute("totalParties", partyService.getAllParties().size());
        model.addAttribute("totalItems", itemService.getAllItems().size());
        model.addAttribute("lowStockItems", itemService.getLowStockItems().size());
        model.addAttribute("customers", partyService.getCustomers().size());
        model.addAttribute("suppliers", partyService.getSuppliers().size());
        
        return "dashboard";
    }
}


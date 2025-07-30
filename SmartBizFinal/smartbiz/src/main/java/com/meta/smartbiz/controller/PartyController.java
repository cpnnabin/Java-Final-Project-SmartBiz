package com.meta.smartbiz.controller;

import com.meta.smartbiz.dto.PartyDto;
import com.meta.smartbiz.entity.PartyType;
import com.meta.smartbiz.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/parties")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @GetMapping
    public String listParties(Model model) {
        model.addAttribute("parties", partyService.getAllParties());
        return "parties/list";
    }

    @GetMapping("/new")
    public String newPartyForm(Model model) {
        model.addAttribute("party", new PartyDto());
        model.addAttribute("partyTypes", PartyType.values());
        return "parties/form";
    }

    @GetMapping("/edit/{id}")
    public String editPartyForm(@PathVariable Long id, Model model) {
        PartyDto party = partyService.getPartyById(id)
                .orElseThrow(() -> new RuntimeException("Party not found"));
        model.addAttribute("party", party);
        model.addAttribute("partyTypes", PartyType.values());
        return "parties/form";
    }

    @PostMapping("/save")
    public String saveParty(@ModelAttribute PartyDto party, RedirectAttributes redirectAttributes) {
        try {
            partyService.saveParty(party);
            redirectAttributes.addFlashAttribute("success", "Party saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error saving party: " + e.getMessage());
        }
        return "redirect:/parties";
    }

    @GetMapping("/delete/{id}")
    public String deleteParty(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            partyService.deleteParty(id);
            redirectAttributes.addFlashAttribute("success", "Party deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting party: " + e.getMessage());
        }
        return "redirect:/parties";
    }

    @GetMapping("/search")
    public String searchParties(@RequestParam String name, Model model) {
        model.addAttribute("parties", partyService.searchParties(name));
        model.addAttribute("searchTerm", name);
        return "parties/list";
    }
}


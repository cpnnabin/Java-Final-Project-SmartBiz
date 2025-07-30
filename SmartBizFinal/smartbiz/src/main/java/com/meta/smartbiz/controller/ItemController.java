package com.meta.smartbiz.controller;

import com.meta.smartbiz.dto.ItemDto;
import com.meta.smartbiz.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public String listItems(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "items/list";
    }

    @GetMapping("/new")
    public String newItemForm(Model model) {
        model.addAttribute("item", new ItemDto());
        return "items/form";
    }

    @GetMapping("/edit/{id}")
    public String editItemForm(@PathVariable Long id, Model model) {
        ItemDto item = itemService.getItemById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        model.addAttribute("item", item);
        return "items/form";
    }

    @PostMapping("/save")
    public String saveItem(@ModelAttribute ItemDto item, RedirectAttributes redirectAttributes) {
        try {
            itemService.saveItem(item);
            redirectAttributes.addFlashAttribute("success", "Item saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error saving item: " + e.getMessage());
        }
        return "redirect:/items";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            itemService.deleteItem(id);
            redirectAttributes.addFlashAttribute("success", "Item deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting item: " + e.getMessage());
        }
        return "redirect:/items";
    }

    @GetMapping("/search")
    public String searchItems(@RequestParam String name, Model model) {
        model.addAttribute("items", itemService.searchItems(name));
        model.addAttribute("searchTerm", name);
        return "items/list";
    }

    @GetMapping("/low-stock")
    public String lowStockItems(Model model) {
        model.addAttribute("items", itemService.getLowStockItems());
        model.addAttribute("lowStock", true);
        return "items/list";
    }
}


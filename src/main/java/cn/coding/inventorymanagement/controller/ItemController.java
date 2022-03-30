package cn.coding.inventorymanagement.controller;

import cn.coding.inventorymanagement.model.Item;
import cn.coding.inventorymanagement.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inventory")
public class ItemController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("item", new Item());
        return "item/item-form";
    }

    @PostMapping("/save")
    public String addItem(Item item, RedirectAttributes redirectAttributes) {
        logger.info("New Item has been save Successfully!");
        redirectAttributes.addFlashAttribute("New Item has been Add Successfully!");
        itemService.save(item);
        return "redirect:/item/list-item";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        try {
            Item item = itemService.getById(id);
            model.addAttribute("item", item);
            return "item/item-form";
        } catch (Exception e) {
            attributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/item/list-item";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            itemService.deleteById(id);
        } catch (Exception e) {
            attributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/item/list-item";
    }

    @GetMapping("/list")
    public String listItems(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                            @RequestParam(value = "size", required = false, defaultValue = "5") int size, Model model){
        model.addAttribute("listItems", itemService.getItemWithPaginated(pageNumber, size));
        return "item/list-item";
    }
}

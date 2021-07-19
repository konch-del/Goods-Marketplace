package ru.netcracker.studentsummer2021.goodsmarketplace.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("text", "Hello, World!");
        return "home";
    }
}

package com.india.railway.controller;




import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Thymeleaf with Spring Boot!");
        List<String> items = Arrays.asList("Item 1", "Item 2", "Item 3");
        model.addAttribute("items", items);
        return "index"; // Refers to src/main/resources/templates/index.html
    }
}
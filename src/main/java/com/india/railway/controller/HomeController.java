package com.india.railway.controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Thymeleaf with Spring Boot!");
        return "index"; // Refers to src/main/resources/templates/index.html
    }
}
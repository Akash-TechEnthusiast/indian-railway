package com.india.railway.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to Thymeleaf with Spring Boot!");
        List<String> items = Arrays.asList("Item 1", "Item 2", "Item 3");
        model.addAttribute("items", items);
        return "index"; // Refers to src/main/resources/templates/index.html
    }

    @GetMapping("/login")
    public String home() {

        return "Helloworld"; // Refers to src/main/resources/templates/index.html
    }

    @RequestMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("welcome_msg", "Global Exception Handler Example");
        // String name = null;
        // name.length();
        return "welcome"; // Refers to src/main/resources/templates/index.html
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(Model model) {
        model.addAttribute("error_msg", "Some problem occured");
        return "error";
    }

}

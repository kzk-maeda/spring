package com.example.spring.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    // Get Controller for Login
    @GetMapping("/login")
    public String getLogin(Model model) {
        // go to login.html
        return "login/login";
    }

    // Post Controller for Login
    @PostMapping("/login")
    public String postLogin(Model model) {
        // go to login.html
        return "login/login";
    }
}

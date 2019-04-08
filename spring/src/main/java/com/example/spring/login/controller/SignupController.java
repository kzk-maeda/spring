package com.example.spring.login.controller;

import java.util.Map;
import java.util.Collections;
import java.util.LinkedHashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    // Radio Button
    private Map<String, String> radioMarriage;

    // Initialize Radio Button
    private Map<String, String> initRadioMarriage() {
        Map<String, String> radio = new LinkedHashMap<>();

        radio.put("既婚", "true");
        radio.put("未婚", "false");

        return radio;
    }

    // Get Controller for Signup
    @GetMapping("/signup")
    public String getSignUp(Model model) {
        radioMarriage = initRadioMarriage();
        System.out.println(radioMarriage);
        model.addAttribute("radioMarriage", radioMarriage);

        return "login/signup";
    }

    // Post Controller for Signup
    @PostMapping("/signup")
    public String postSignUp(Model mode) {
        // redirect to login.html
        return "redirect:/login";
    }
}

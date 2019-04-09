package com.example.spring.login.controller;

import com.example.spring.login.domain.model.SignupForm;
import java.util.Map;
import java.util.Collections;
import java.util.LinkedHashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    // Radio Button
    private Map<String, String> radioMarriage;

    // Initialize Radio Button
    private Map<String, String> initRadioMarriage() {
        Map<String, String> radio = new LinkedHashMap<String, String>();

        radio.put("既婚", "true");
        radio.put("未婚", "false");

        return radio;
    }

    // Get Controller for Signup
    @GetMapping("/signup")
    public String getSignUp(@ModelAttribute SignupForm form, Model model) {
        radioMarriage = initRadioMarriage();
        model.addAttribute("radioMarriage", radioMarriage);

        return "login/signup";
    }

    // Post Controller for Signup
    @PostMapping("/signup")
    public String postSignUp(@ModelAttribute @Validated SignupForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return getSignUp(form, model);
        }
        System.out.println(form);

        // redirect to login.html
        return "redirect:/login";
    }
}

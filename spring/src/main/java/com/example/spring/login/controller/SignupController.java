package com.example.spring.login.controller;

import com.example.spring.login.domain.model.SignupForm;
import com.example.spring.login.domain.service.UserService;
import com.example.spring.login.domain.model.User;
import java.util.Map;
import java.util.Collections;
import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;

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

        // insert
        User user = new User();

        user.setUserId(form.getUserId());
        user.setPassword(form.getPassword());
        user.setUserName(form.getUserName());
        user.setBirthday(form.getBirthday());
        user.setAge(form.getAge());
        user.setMarriage(form.isMarriage());
        user.setRole("ROLE_GENERAL");

        // register user
        boolean result = userService.insert(user);

        // jadge user register
        if (result == true) {
            System.out.println("Insert Succeeded");
        } else {
            System.out.println("Insert Failed");
        }

        // redirect to login.html
        return "redirect:/login";
    }
}

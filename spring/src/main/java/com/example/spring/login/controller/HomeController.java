package com.example.spring.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.spring.login.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    // ユーザー一覧画面取得用メソッド
    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("contents","login/home::home_contents");

        return "login/homeLayout";
    }

    // ログアウト用メソッド
    @PostMapping("/logout")
    public String postLogout() {
        return "redirect:/login";
    }
}

package com.example.spring.login.controller;

import java.util.List;
import com.example.spring.login.domain.model.User;
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

    // Home面取得用メソッド
    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("contents","login/home::home_contents");

        return "login/homeLayout";
    }

    // ユーザー一覧取得画面用メソッド
    @GetMapping("/userList")
    public String getUserList(Model model) {

        model.addAttribute("contents", "login/userList :: userList_contents");

        // Generate User List
        List<User> userList = userService.selectMany();

        // Modelにユーザーリストを登録
        model.addAttribute("userList", userList);

        // データ件数を取得
        int count = userService.count();
        model.addAttribute("userListCount", count);

        return "login//homeLayout";
    }

    // ログアウト用メソッド
    @PostMapping("/logout")
    public String postLogout() {
        return "redirect:/login";
    }

    // ユーザー一覧のCSV出力用メソッド
    @GetMapping("/userList/csv")
    public String getUserListCsv(Model model) {
        return getUserList(model);
    }
}

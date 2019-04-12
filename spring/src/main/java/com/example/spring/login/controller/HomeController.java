package com.example.spring.login.controller;

import com.example.spring.login.domain.model.SignupForm;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import com.example.spring.login.domain.model.User;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.spring.login.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    // Radio Button
    private Map<String, String> radioMarriage;

    // Initialize Radio Button
    private Map<String, String> initRadioMarriage() {
        Map<String, String> radio = new LinkedHashMap<>();

        radio.put("既婚", "true");
        radio.put("未婚", "false");

        return radio;
    }

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

    // ユーザー詳細画面用メソッド
    @GetMapping("/userDetail/{id:.+}")
    public String getUserDetail(@ModelAttribute SignupForm form, Model model,
            @PathVariable("id") String userId) {
        // for debug
        System.out.println("userId = " + userId);

        // Contentに詳細を表示するための文字列を登録
        model.addAttribute("contents", "login/userDetail :: userDetail_contents");

        // Radioの初期化
        radioMarriage = initRadioMarriage();
        model.addAttribute("radioMarriage", radioMarriage);

        // check user id
        if (userId != null && userId.length() > 0) {
            User user = userService.selectOne(userId);

            form.setUserId(user.getUserId());
            form.setUserName(user.getUserName());
            form.setBirthday(user.getBirthday());
            form.setAge(user.getAge());
            form.setMarriage(user.isMarriage());

            // register model
            model.addAttribute("signupForm", form);
        }

        return "login/homeLayout";
    }

    // ユーザー更新用メソッド
    @PostMapping(value = "/userDetail", params = "update")
    public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
        // for debug
        System.out.println("Update Exec Start");

        // create User Instance
        User user = new User();

        // Exchange form class to user class
        user.setUserId(form.getUserId());
        user.setPassword(form.getPassword());
        user.setUserName(form.getUserName());
        user.setBirthday(form.getBirthday());
        user.setAge(form.getAge());
        user.setMarriage(user.isMarriage());

        // update execution
        boolean result = userService.updateOne(user);

        if (result == true) {
            model.addAttribute("result", "更新成功");
        } else {
            model.addAttribute("result", "更新失敗");
        }

        return getUserList(model);
    }

    // ユーザー削除用メソッド
    @PostMapping(value = "userDetail", params = "delete")
    public String postUserDetailDelete(@ModelAttribute SignupForm form, Model model) {
        // for debug
        System.out.println("Delete Exec Start");

        // delete execution
        boolean result = userService.deleteOne(form.getUserId());

        if (result == true) {
            model.addAttribute("削除成功");
        } else {
            model.addAttribute("削除失敗");
        }

        return getUserList(model);
    }

    // ログアウト用メソッド
    @PostMapping("/logout")
    public String postLogout() {
        return "redirect:/login";
    }

    // ユーザー一覧のCSV出力用メソッド
    @GetMapping("/userList/csv")
    public ResponseEntity<byte[]> getUserListCsv(Model model) {
        // get all user and save csv
        userService.userCsvOut();
        byte[] bytes = null;

        try {
            bytes = userService.getFile("sample.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // set HTTP header
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "text/csv; charset=UTF8");
        header.setContentDispositionFormData("filename", "sample.csv");

        return new ResponseEntity<>(bytes, header, HttpStatus.OK);
    }

    // Admin権限専用画面のGETメソッド
    @GetMapping("/admin")
    public String getAdmin(Model model) {
        model.addAttribute("contents", "login/admin :: admin_contents");

        return "login/homeLayout";
    }
}

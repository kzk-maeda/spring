package com.example.spring.login.controller;

import com.example.spring.login.domain.service.RestService;
import com.example.spring.login.domain.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    RestService service;

    // return json for method (POST|PUT|DELETE)
    private String returnResultJson(boolean result) {
        String str = "";

        if (result) {
            str = "{\"result\":\"ok\"}";
        } else {
            str = "{\"result\":\"error\"}";
        }

        return str;
    }

    // get all user
    @GetMapping("/rest/get")
    public List<User> getUserMany() {
        return service.selectMany();
    }

    // get single user
    @GetMapping("/rest/get/{id:.+}")
    public User getUserOne(@PathVariable("id") String userId) {
        return service.selectOne(userId);
    }

    // insert single user
    @PostMapping("/rest/insert")
    public String postUserOne(@RequestBody User user) {
        boolean result = service.insert(user);
        return returnResultJson(result);
    }

    // update single user
    @PutMapping("/rest/update")
    public String putUserOne(@RequestBody User user) {
        boolean result = service.update(user);
        return returnResultJson(result);
    }

    // delete single user
    @DeleteMapping("/rest/delete/{id:.+}")
    public String deleteUserOne(@PathVariable("id") String userId) {
        boolean result = service.delete(userId);
        return returnResultJson(result);
    }

}

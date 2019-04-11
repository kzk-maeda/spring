package com.example.spring.login.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spring.login.domain.repository.UserDao;
import com.example.spring.login.domain.model.User;


@Service
public class UserService {

    @Autowired
    UserDao dao;

    // insert method
    public boolean insert(User user) {
        // execute insert
        int rowNumber = dao.insertOne(user);

        // jadge
        boolean result = false;

        if (rowNumber > 0) {
            // success
            result = true;
        }

        return result;
    }

    // count method
    public int count() {
        return dao.count();
    }

    // get all data method
    public List<User> selectMany() {
        return dao.selectMany();
    }
}

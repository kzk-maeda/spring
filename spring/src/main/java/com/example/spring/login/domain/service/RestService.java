package com.example.spring.login.domain.service;

import java.util.List;
import com.example.spring.login.domain.model.User;

public interface RestService {

    // single register method
    public boolean insert(User user);

    // single query method
    public User selectOne(String userId);

    // all query method
    public List<User> selectMany();

    // single update method
    public boolean update(User user);

    // single delete method
    public boolean delete(String userId);
}

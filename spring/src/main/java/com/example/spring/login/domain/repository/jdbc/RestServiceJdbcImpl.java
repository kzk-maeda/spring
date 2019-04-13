package com.example.spring.login.domain.repository.jdbc;

import java.util.List;
import com.example.spring.login.domain.repository.UserDao;
import com.example.spring.login.domain.service.RestService;
import com.example.spring.login.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RestServiceJdbcImpl implements RestService {

    @Autowired
    @Qualifier("UserDaoJdbcImpl")
    UserDao dao;

    // return boolean result
    private boolean returnBooleanResult(int result) {
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    // single register method
    @Override
    public boolean insert(User user) {
        int result = dao.insertOne(user);
        return returnBooleanResult(result);
    }

    // single query method
    @Override
    public User selectOne(String userId) {
        return dao.selectOne(userId);
    }

    // all query method
    @Override
    public List<User> selectMany() {
        return dao.selectMany();
    }

    // single update method
    @Override
    public boolean update(User user) {
        int result = dao.updateOne(user);
        return returnBooleanResult(result);
    }

    // single delete method
    @Override
    public boolean delete(String userId) {
        int result = dao.deleteOne(userId);
        return returnBooleanResult(result);
    }
}

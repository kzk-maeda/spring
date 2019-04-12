package com.example.spring.login.domain.service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.example.spring.login.domain.repository.UserDao;
import com.example.spring.login.domain.model.User;


@Service
public class UserService {

    @Autowired
    @Qualifier("UserDaoJdbcImpl")
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

    // get single data method
    public User selectOne(String userId) {
        return dao.selectOne(userId);
    }

    // update user data method
    public boolean updateOne(User user) {
        // execute update
        int rowNumber = dao.updateOne(user);

        // jadge
        boolean result = false;

        if (rowNumber > 0) {
            // success
            result = true;
        }

        return result;
    }

    // delete user method
    public boolean deleteOne(String userId) {
        // execute delete
        int rowNumber = dao.deleteOne(userId);

        // jadge
        boolean result = false;

        if (rowNumber > 0) {
            // success
            result = true;
        }

        return result;
    }

    // output csv
    public void userCsvOut() throws DataAccessException {
        dao.userCsvOut();
    }

    // get file saved at server, and change byte
    public byte[] getFile(String fileName) throws IOException {
        // get file system
        FileSystem fs = FileSystems.getDefault();

        // get file
        Path p = fs.getPath(fileName);

        // format byte strings
        byte[] bytes = Files.readAllBytes(p);

        return bytes;
    }
}

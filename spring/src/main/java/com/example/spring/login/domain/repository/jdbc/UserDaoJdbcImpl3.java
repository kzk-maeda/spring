package com.example.spring.login.domain.repository.jdbc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.spring.login.domain.model.User;

@Repository("UserDaoJdbcImpl3")
public class UserDaoJdbcImpl3 extends UserDaoJdbcImpl {

    @Autowired
    private JdbcTemplate jdbc;

    // get user
    @Override
    public User selectOne(String userId) {

        // sql
        String sql = "SELECT * FROM m_user WHERE user_id = ?";

        // RowMapper
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

        // execute sql
        return jdbc.queryForObject(sql, rowMapper, userId);
    }

    // get all user
    @Override
    public List<User> selectMany() {

        // sql
        String sql = "SELECT * FROM m_user";

        // RowMapper
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

        // execute sql
        return jdbc.query(sql, rowMapper);
    }
}

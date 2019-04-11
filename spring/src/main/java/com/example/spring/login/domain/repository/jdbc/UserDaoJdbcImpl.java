package com.example.spring.login.domain.repository.jdbc;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.spring.login.domain.model.User;
import com.example.spring.login.domain.repository.UserDao;

@Repository
public class UserDaoJdbcImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbc;

    // Userテーブルの件数を取得
    @Override
    public int count() throws DataAccessException {
        // count
        return jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
    }

    // Userテーブルにデータを１件投入
    @Override
    public int insertOne(User user) throws DataAccessException {
        // insert
        return jdbc.update("INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)"
            + "VALUES(?, ?, ?, ?, ?, ?, ?)"
            ,user.getUserId()
            ,user.getPassword()
            ,user.getUserName()
            ,user.getBirthday()
            ,user.getAge()
            ,user.isMarriage()
            ,user.getRole());

    }

    // Userテーブルのデータを１件取得
    @Override
    public User selectOne(String userId) throws DataAccessException {
        // select
        Map<String, Object> map = jdbc.queryForMap("SELECT * FROM m_user WHERE user_id = ?"
            , userId);
        // User Instance Create
        User user = new User();

        // set data to User Instance
        user.setUserId((String)map.get("user_id"));
        user.setPassword((String)map.get("password"));
        user.setUserName((String)map.get("user_name"));
        user.setBirthday((Date)map.get("birthday"));
        user.setAge((Integer)map.get("age"));
        user.setMarriage((Boolean)map.get("marriage"));
        user.setRole((String)map.get("role"));

        return user;
    }

    // Userテーブルの全データを取得
    @Override
    public List<User> selectMany() throws DataAccessException {
        // get all data
        List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");

        // value for result
        List<User> userList = new ArrayList<>();

        // insert result to list
        for (Map<String, Object> map: getList) {
            // User instance create
            User user = new User();

            // set data to User instance
            user.setUserId((String)map.get("user_id"));
            user.setPassword((String)map.get("password"));
            user.setUserName((String)map.get("user_name"));
            user.setBirthday((Date)map.get("birthday"));
            user.setAge((Integer)map.get("age"));
            user.setMarriage((Boolean)map.get("marriage"));
            user.setRole((String)map.get("role"));

            // add result list
            userList.add(user);
        }

        return userList;
    }

    // Userテーブルの値を１件更新
    @Override
    public int updateOne(User user) throws DataAccessException {
        return jdbc.update("UPDATE M_USER"
                        + " SET"
                        + " password = ?,"
                        + " user_name = ?,"
                        + " birthday = ?,"
                        + " age = ?,"
                        + " marriage = ?"
                        + " WHERE user_id = ?",
                user.getPassword(),
                user.getUserName(),
                user.getBirthday(),
                user.getAge(),
                user.isMarriage(),
                user.getUserId());
    }

    // Userテーブルを１件削除
    @Override
    public int deleteOne(String userId) throws DataAccessException {
        return jdbc.update("Delete FROM m_user WHERE user_id = ?", userId);
    }

    // SQL結果をサーバにcsvで保存する
    @Override
    public void userCsvOut() throws DataAccessException {

    }
}

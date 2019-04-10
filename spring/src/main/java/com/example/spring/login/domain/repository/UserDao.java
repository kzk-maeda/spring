package com.example.spring.login.domain.repository;

import java.util.List;
import javax.xml.crypto.Data;
import org.springframework.dao.DataAccessException;
import com.example.spring.login.domain.model.User;


public interface UserDao {

    // Userテーブルの件数を取得
    public int count() throws DataAccessException;

    // Userテーブルにデータを１件投入
    public int insertOne(User user) throws DataAccessException;

    // Userテーブルのデータを１件取得
    public User selectOne(String userId) throws DataAccessException;

    // Userテーブルの全データを取得
    public List<User> seletMeny() throws DataAccessException;

    // Userテーブルの値を１件更新
    public int updateOne(User user) throws DataAccessException;

    // Userテーブルを１件削除
    public int deleteOne(String userId) throws DataAccessException;

    // SQL結果をサーバにcsvで保存する
    public void userCsvOut() throws DataAccessException;

}

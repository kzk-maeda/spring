package com.example.spring.login.domain.repository.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowCallbackHandler;


public class UserRowCallbackHandler implements RowCallbackHandler {

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        try {

            // prepare file read
            File file = new File("sample.csv");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            // loop
            do {
                String str = rs.getString("user_id") + ","
                        + rs.getString("password") + ","
                        + rs.getString("user_name") + ","
                        + rs.getDate("birthday") + ","
                        + rs.getInt("age") + ","
                        + rs.getBoolean("marriage") + ","
                        + rs.getString("role");

                // write file
                bw.write(str);
                bw.newLine();
            } while(rs.next());

            // Force write & close
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }
}

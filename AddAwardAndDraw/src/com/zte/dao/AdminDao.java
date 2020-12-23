package com.zte.dao;

import com.zte.model.Admin;
import com.zte.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminDao {
    public boolean comparePassword(Admin admin) {
        String sql = String.format("select password from Admin where username=%s;", admin.getUsername());
        Connection connection = DBUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String password = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, statement, connection);
        }

        assert password != null;
        return password.equals(admin.getPassword());
    }
}

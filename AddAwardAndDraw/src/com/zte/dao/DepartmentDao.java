package com.zte.dao;

import com.zte.model.Department;
import com.zte.util.DBUtil;
import com.zte.util.common.Tuple;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {
    public List<Department> searchAll() {
        String sql = String.format("select * from department;");

        List<Department> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Department department = null;

            while (resultSet.next()) {
                String abbreviation = resultSet.getString("abbr");
                String fullname = resultSet.getString("name");
                int totalnum = resultSet.getInt("numberOfMember");

                department = new Department(abbreviation, fullname, totalnum);
                list.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, statement, connection);
        }

        return list;
    }

    public int getNumOfDepartments() {
        String sql = String.format("select count(abbr) from department;");

        int result = 0;
        Connection connection = DBUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                result++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, statement, connection);
        }

        return result;
    }
}

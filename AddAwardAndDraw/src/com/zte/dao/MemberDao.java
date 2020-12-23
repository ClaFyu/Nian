package com.zte.dao;

import com.zte.util.ConfigUtil;
import com.zte.util.DBUtil;
import com.zte.util.common.Tuple;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberDao {
    public boolean updateAwardFlag(String departmentAbbr, String awardInfo, String jobNumber) {
        String sql = String.format("update member set flagOfAward='%s' where jobNum='%s';", awardInfo, jobNumber);
        boolean f = false;

        Connection connection = DBUtil.getConnection();
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.execute(sql);

            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(statement, connection);
        }

        return f;
    }

    public String returnStatus(String departmentAbbr, Integer id, int totalNumber) {
        Integer idTmp = id;
        String sql = String.format("select name, jobNumber, flagOfAward from member where id=%s and department='%s';",
                idTmp.toString(), departmentAbbr);

        Connection connection = DBUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String memberName = null;

        try {
            String name;
            String number;
            String flag;

            while (true) {
                name = null;
                number = null;
                flag = null;

                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    name = resultSet.getString("name");
                    number = resultSet.getString("jobNumber");
                    flag = resultSet.getString("flagOfAward");
                }

                if ((null == flag) && (null != name) && (null != number)) {
                    memberName = name + number;
                    ConfigUtil.luckyMember.add(id);
                    break;
                } else {
                    do {
                        idTmp++;
                        idTmp = (idTmp > totalNumber) ? idTmp % totalNumber : idTmp;
                    } while (ConfigUtil.luckyMember.contains(idTmp));
                    sql = String.format("select name, jobNumber, flagOfAward from member where id=%s and department='%s';",
                            idTmp.toString(), departmentAbbr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(resultSet, statement, connection);
        }

        return memberName;
    }
}

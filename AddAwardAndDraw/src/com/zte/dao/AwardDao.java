package com.zte.dao;

import com.zte.model.Award;
import com.zte.util.ConfigUtil;
import com.zte.util.DBUtil;
import com.zte.util.common.Triplet;
import com.zte.util.common.Tuple;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AwardDao {
    public boolean addAward(Award award) {
        boolean f = false;
        String sql = null;

        Connection connection = DBUtil.getConnection();
        Statement statement = null;

        if ("".equals(award.getLeader())) {
            sql = String.format("insert into Award(abbr, name, leader, leaderjobnumber, moneyperperson, " +
                            "totalnumberofmember, restNum, restNumFor01, restNumFor02) " +
                            "values('%s', '%s', null, null, %s, %s, %s, %s, %s);",
                    award.getAbbr(), award.getName(), award.getMoneyPerPerson(), award.getTotalNumberOfMember(),
                    award.getRestNum(), award.getRestNumFor01(), award.getRestNumFor02());
        } else {
            sql = String.format("insert into Award(abbr, name, leader, leaderjobnumber, moneyperperson, " +
                            "totalnumberofmember, restNum, restNumFor01, restNumFor02) " +
                            "values('%s', '%s', '%s', '%s', %s, %s, %s, %s, %s);",
                    award.getAbbr(), award.getName(), award.getLeader(), award.getLeaderJobNumber(),
                    award.getMoneyPerPerson(), award.getTotalNumberOfMember(), award.getRestNum(),
                    award.getRestNumFor01(), award.getRestNumFor02());
        }

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

    public boolean updateNewAwardInfo(List<Tuple<String, String>> list, String awardAbbr,
                                      Triplet<Integer, Integer, Integer> triplet) {
        boolean f = false;

        if (list.size() != 0) {
            Connection connection = DBUtil.getConnection();
            Statement statement = null;

            try {
                for (Tuple<String, String> stringStringTuple : list) {
                    String[] strings = ConfigUtil.splitNameAndNumber(stringStringTuple.getFirst());
                    String name = strings[0];
                    String number = strings[1];
                    String department = stringStringTuple.getSecond();

                    String sql = String.format("update member set flagOfAward='%s' where name='%s' and jobNumber='%s';",
                            awardAbbr, name, number);

                    statement = connection.createStatement();
                    statement.execute(sql);
                }

                String sql = String.format("update award set restNum=%s, restNumFor01=%s, restNumFor02=%s where abbr='%s';",
                        String.valueOf(triplet.getFirst()), String.valueOf(triplet.getSecond()), String.valueOf(triplet.getThird()),
                        awardAbbr);
                statement = connection.createStatement();
                statement.execute(sql);

                f = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(statement, connection);
            }
        }

        return f;
    }

    public List<Award> searchAll() {
        String sql = String.format("select * from Award;");

        List<Award> list = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Award award = null;

            while (resultSet.next()) {
                String abbr = resultSet.getString("abbr");
                String name = resultSet.getString("name");
                String leader = resultSet.getString("leader");
                String leaderJobNumber = resultSet.getString("leaderJobNumber");
                int money = resultSet.getInt("moneyPerPerson");
                int member = resultSet.getInt("totalNumberOfMember");
                int restNum = resultSet.getInt("restNum");
                int restNumFor01 = resultSet.getInt("restNumFor01");
                int restNumFor02 = resultSet.getInt("restNumFor02");

                award = new Award(abbr, name, leader, leaderJobNumber, money, member, restNum, restNumFor01, restNumFor02);
                list.add(award);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, statement, connection);
        }

        return list;
    }
}

package com.zte.servlet;

import com.zte.dao.AwardDao;
import com.zte.dao.MemberDao;
import com.zte.model.Award;
import com.zte.model.FrontAllAwardInfoNow;
import com.zte.util.ConfigUtil;
import com.zte.util.common.Triplet;
import com.zte.util.common.Tuple;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.IntStream;

public class AddAwardServlet extends HttpServlet  {
    private boolean getNameList(String awardAbbr, String awardName, int[] list, int totalNum) {
        Random random = new Random();
        MemberDao memberDao = new MemberDao();
        List<Tuple<String, String>> people = new ArrayList<>();

        int persons = ConfigUtil.departmentInfo.get("01").getSecond() + ConfigUtil.departmentInfo.get("02").getSecond();

        for (int i = 0; i < list[0] ;i++) {
            int randomNum = random.nextInt(persons) + 1;

            while (ConfigUtil.luckyMember.contains(randomNum)) {
                randomNum = random.nextInt(persons) + 1;
            }

            String result = memberDao.returnStatus("01", randomNum, persons);

            if (result == null) {
                return false;
            } else {
                people.add(new Tuple<>(result, "01"));
            }
        }

        for (int i = 0; i < list[1] ;i++) {
            int randomNum = random.nextInt(persons) + 1;

            while (ConfigUtil.luckyMember.contains(randomNum)) {
                randomNum = random.nextInt(persons) + 1;
            }

            String result = memberDao.returnStatus("02", randomNum, persons);

            if (result == null) {
                return false;
            } else {
                people.add(new Tuple<>(result, "02"));
            }
        }

        Collections.shuffle(people);
        ConfigUtil.awardLuckyList.put(awardAbbr, new Triplet<>(awardName, new Triplet<>(totalNum, list[0], list[1]), people));

        return true;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String awardName = request.getParameter("awardname");
        String leader = request.getParameter("leader");
        int moneyPerPerson = Integer.parseInt(request.getParameter("moneyperperson"));
        int tOrs = Integer.parseInt(request.getParameter("print"));
        int[] persons = new int[2];
        IntStream is = null;

        String[] leaderInfo = null;
        String leaderName = "";
        String leaderJobNumber = "";
        if (!"".equals(leader)) {
            leaderInfo = ConfigUtil.splitNameAndNumber(leader);
            leaderName = leaderInfo[0];
            leaderJobNumber = leaderInfo[1];
        }

        Random random = new Random();
        int result;

        // 根据前端添加人数的方式，为每个部门计算应得的人数
        // 若是给一个总人数则按照部门人数比例分配，否则按照给定的数量分配
        if (tOrs == 0) {
            int totalNum = Integer.parseInt(request.getParameter("0000"));

            BigDecimal a1 = new BigDecimal(Double.toString(ConfigUtil.memberRate.get("01")));
            BigDecimal a2 = new BigDecimal(Double.toString(ConfigUtil.memberRate.get("02")));
            BigDecimal b = new BigDecimal(Double.toString(totalNum));

            persons[0] = a1.multiply(b).intValue();
            persons[1] = a2.multiply(b).intValue();

            is = Arrays.stream(persons);
            result = is.sum();
            if (result != totalNum) {
                int randomNum = random.nextInt(2);
                persons[randomNum] -= (result - totalNum);
                result = totalNum;
            }
        } else {
            persons[0] = Integer.parseInt(request.getParameter("0001"));
            persons[1] = Integer.parseInt(request.getParameter("0002"));
            is = Arrays.stream(persons);
            result = is.sum();
        }

        // 随机抽取该奖项的获奖者
        String abbr = ConfigUtil.numToStrN(ConfigUtil.awardLuckyList.size(), 2);
        boolean result1 = getNameList(abbr, awardName, persons, result);
        // 将奖项写入数据库
        AwardDao awardDao = new AwardDao();
        boolean result2 = awardDao.addAward(new Award(abbr, awardName, leaderName, leaderJobNumber, moneyPerPerson,
                result, result, persons[0], persons[1]));

        if (result1 && result2) {
            JOptionPane.showMessageDialog(null, "添加成功");

            request.setAttribute("awardList", ConfigUtil.returnDrawList());
            request.getRequestDispatcher("drawing.jsp").forward(request, response);
        } else {
            JOptionPane.showMessageDialog(null, "添加失败");
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}

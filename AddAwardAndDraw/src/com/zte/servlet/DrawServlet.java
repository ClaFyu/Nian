package com.zte.servlet;

import com.zte.dao.AwardDao;
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
import java.util.ArrayList;
import java.util.List;

public class DrawServlet extends HttpServlet  {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String awardName = request.getParameter("awardAttribution");
        String numOfPerson = request.getParameter("getNums");

        int num = Integer.parseInt(numOfPerson);
        int restTotalNum = ConfigUtil.awardLuckyList.get(awardName).getSecond().getFirst();   // 单独写出来可以不在循环中对 awardAndLuckyInfo 写入
        int restD1Num = ConfigUtil.awardLuckyList.get(awardName).getSecond().getSecond();
        int restD2Num = ConfigUtil.awardLuckyList.get(awardName).getSecond().getThird();
        num = Math.min(num, restTotalNum);

        if (num == 0) {
            JOptionPane.showMessageDialog(null, "此奖已抽完，请抽取其他奖项");

            request.setAttribute("awardList", ConfigUtil.returnDrawList());
            request.getRequestDispatcher("drawing.jsp").forward(request, response);
        }

        int newRestNum = restTotalNum - num;

        List<Tuple<String, String>> list = ConfigUtil.awardLuckyList.get(awardName).getThird().
                subList(newRestNum, restTotalNum);

        for (Tuple<String, String> tuple: list) {
            if (tuple.getSecond().equals("01")) {
                restD1Num--;
            } else {
                restD2Num--;
            }
        }

        ConfigUtil.awardLuckyList.get(awardName).setSecond(new Triplet<>(newRestNum, restD1Num, restD2Num));

        boolean result = false;
        // 抽奖完成后直接写会数据库
        if (list.size() > 0) {
            AwardDao awardDao = new AwardDao();
            result = awardDao.updateNewAwardInfo(list, awardName, ConfigUtil.awardLuckyList.get(awardName).getSecond());
        }

        // 需要把这个 List 返回到主界面上

        if (list.size() > 0 && result) {
            JOptionPane.showMessageDialog(null, "抽奖完成");

            request.setAttribute("awardList", ConfigUtil.returnDrawList());
            request.getRequestDispatcher("drawing.jsp").forward(request, response);
        } else {
            JOptionPane.showMessageDialog(null, "抽奖失败");
            response.sendRedirect("drawing.jsp");
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

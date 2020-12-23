package com.zte.servlet;

import com.zte.model.FrontAllAwardInfoNow;
import com.zte.util.ConfigUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JumpServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String page = request.getParameter("page");

        if (page.equals("0")) {
            List<FrontAllAwardInfoNow> list = new ArrayList<>();

            for (String abbr: ConfigUtil.awardLuckyList.keySet()) {
                FrontAllAwardInfoNow frontAllAwardInfoNow = new FrontAllAwardInfoNow();

                frontAllAwardInfoNow.setAbbr(abbr);
                frontAllAwardInfoNow.setName(ConfigUtil.awardLuckyList.get(abbr).getFirst());
                frontAllAwardInfoNow.setRestNum(ConfigUtil.awardLuckyList.get(abbr).getSecond().getFirst());
                list.add(frontAllAwardInfoNow);
            }

            request.setAttribute("awardList", list);
            request.getRequestDispatcher("drawing.jsp").forward(request, response);
        } else if (page.equals("1")) {
            request.getRequestDispatcher("addaward.jsp").forward(request, response);
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

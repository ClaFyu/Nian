package com.zte.servlet;

import com.zte.dao.AdminDao;
import com.zte.model.Admin;
import com.zte.model.FrontAllAwardInfoNow;
import com.zte.util.ConfigUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminLoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String loginName = request.getParameter("username");
        String loginPassword = request.getParameter("password");

        Admin admin = new Admin(loginName, loginPassword);
        AdminDao adminDao = new AdminDao();
        boolean result = adminDao.comparePassword(admin);

        if (result) {
            ConfigUtil.resetAllList();
        }

        if (result) {
            JOptionPane.showMessageDialog(null, "登录成功");

            request.setAttribute("awardList", ConfigUtil.returnDrawList());
            request.getRequestDispatcher("drawing.jsp").forward(request, response);
//            request.getRequestDispatcher("drawing.jsp").forward(request, response);
        } else {
            JOptionPane.showMessageDialog(null, "用户名或密码不正确");
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

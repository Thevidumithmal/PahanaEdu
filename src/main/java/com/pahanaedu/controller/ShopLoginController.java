package com.pahanaedu.controller;


import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/shopLogin")
public class ShopLoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            UserService userService = new UserService(conn);
            User user = userService.login(username, password, "shopworker");

            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("shopworker", user);
                resp.sendRedirect("jsp/shopDashboard.jsp");
            } else {
                req.setAttribute("error", "Invalid credentials.");
                req.getRequestDispatcher("jsp/shopLogin.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException("Login failed", e);
        }
    }
}

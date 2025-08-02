package com.pahanaedu.servlet;

import com.pahanaedu.business.user.service.UserService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/changePassword")
public class ChangePasswordController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String phone = req.getParameter("phone");
        String newPassword = req.getParameter("newPassword");

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            UserService userService = new UserService(conn);

            boolean updated = userService.resetPassword(username, phone, newPassword);

            if (updated) {
                req.setAttribute("success", "Password updated successfully.");
            } else {
                req.setAttribute("error", "Invalid username or phone number.");
            }

            req.getRequestDispatcher("jsp/changePassword.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Password change error", e);
        }
    }
}


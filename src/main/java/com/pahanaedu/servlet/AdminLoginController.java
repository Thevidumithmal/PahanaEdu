package com.pahanaedu.servlet;

import com.pahanaedu.business.user.dto.UserDTO;
import com.pahanaedu.business.user.mapper.UserMapper;
import com.pahanaedu.business.user.model.User;
import com.pahanaedu.business.user.service.UserService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/adminLogin")
public class AdminLoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection connection = DBUtil.getInstance().getConnection()) {
            UserService userService = new UserService(connection);
            User adminEntity = userService.login(username, password, "admin");

            if (adminEntity != null) {
                UserDTO adminDTO = UserMapper.toDto(adminEntity);
                HttpSession session = req.getSession();
                session.setAttribute("admin", adminDTO);
                resp.sendRedirect("jsp/adminDashboard.jsp");
            } else {
                req.setAttribute("error", "Invalid credentials.");
                req.getRequestDispatcher("jsp/adminLogin.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            throw new ServletException("Login failed", e);
        }
    }
}

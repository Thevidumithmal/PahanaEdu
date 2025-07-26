package com.pahanaedu.controller;


import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;
import com.pahanaedu.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/addShopWorker")
public class AddShopWorkerController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        User user = new User.Builder()
                .setUsername(username)
                .setPassword(password)
                .setPhone(phone)
                .setAddress(address)
                .setRole("shopworker")
                .build();

        try (Connection connection = DBUtil.getInstance().getConnection()) {
            UserService userService = new UserService(connection);
            boolean success = userService.addUser(user);

            if (success) {
                req.setAttribute("success", "Shop worker added successfully.");
            } else {
                req.setAttribute("error", "Failed to add shop worker (possibly duplicate username).");
            }
            req.getRequestDispatcher("jsp/addShopWorker.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error adding shop worker", e);
        }
    }
}


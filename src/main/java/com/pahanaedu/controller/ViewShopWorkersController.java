package com.pahanaedu.controller;

import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/viewShopWorkers")
public class ViewShopWorkersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try (Connection connection = DBUtil.getInstance().getConnection()) {
            UserService userService = new UserService(connection);
            List<User> shopWorkers = userService.getUsersByRole("shopworker");

            req.setAttribute("shopWorkers", shopWorkers);
            req.getRequestDispatcher("jsp/viewShopWorkers.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Unable to load shop workers", e);
        }
    }
}


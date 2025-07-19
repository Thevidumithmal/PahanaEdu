package com.pahanaedu.controller;


import com.pahanaedu.service.UserService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/deleteShopWorker")
public class DeleteShopWorkerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            UserService userService = new UserService(conn);
            userService.deleteUser(id);
            resp.sendRedirect("viewShopWorkers");

        } catch (Exception e) {
            throw new ServletException("Error deleting worker", e);
        }
    }
}


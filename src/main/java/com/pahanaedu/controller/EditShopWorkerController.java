package com.pahanaedu.controller;

import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/editShopWorker")
public class EditShopWorkerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            UserService userService = new UserService(conn);
            User worker = userService.getUserById(id);

            req.setAttribute("worker", worker);
            req.getRequestDispatcher("jsp/editShopWorker.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Error loading worker for edit", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            UserService userService = new UserService(conn);
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setPhone(phone);
            user.setAddress(address);

            boolean updated = userService.updateUser(user);
            if (updated) {
                resp.sendRedirect("viewShopWorkers");
            } else {
                req.setAttribute("error", "Failed to update user.");
                req.getRequestDispatcher("jsp/editShopWorker.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            throw new ServletException("Error updating worker", e);
        }
    }
}


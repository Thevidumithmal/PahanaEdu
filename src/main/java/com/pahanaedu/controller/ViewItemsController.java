package com.pahanaedu.controller;

import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/viewItems")
public class ViewItemsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            ItemService itemService = new ItemService(conn);
            List<Item> items = itemService.getAllItems();
            req.setAttribute("items", items);
            req.getRequestDispatcher("jsp/viewItems.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Failed to load items", e);
        }
    }
}


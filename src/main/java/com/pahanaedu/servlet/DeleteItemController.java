package com.pahanaedu.servlet;

import com.pahanaedu.business.item.service.ItemService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/deleteItem")
public class DeleteItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            ItemService itemService = new ItemService(conn);
            itemService.deleteItem(id);
            resp.sendRedirect("viewItems");

        } catch (Exception e) {
            throw new ServletException("Failed to delete item", e);
        }
    }
}

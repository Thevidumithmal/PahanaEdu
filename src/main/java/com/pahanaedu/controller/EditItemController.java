package com.pahanaedu.controller;

import com.pahanaedu.model.Item;
import com.pahanaedu.service.ItemService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

@WebServlet("/editItem")
public class EditItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            ItemService itemService = new ItemService(conn);
            Item item = itemService.getItemById(id);
            req.setAttribute("item", item);
            req.getRequestDispatcher("jsp/editItem.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Failed to load item for editing", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        BigDecimal price = new BigDecimal(req.getParameter("price"));

        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setPrice(price);

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            ItemService itemService = new ItemService(conn);
            itemService.updateItem(item);
            resp.sendRedirect("viewItems");

        } catch (Exception e) {
            throw new ServletException("Failed to update item", e);
        }
    }
}

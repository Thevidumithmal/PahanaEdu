package com.pahanaedu.servlet;

import com.pahanaedu.business.item.dto.ItemDTO;
import com.pahanaedu.business.item.service.ItemService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;

@WebServlet("/addItem")
public class AddItemController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        BigDecimal price = new BigDecimal(req.getParameter("price"));

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(name);
        itemDTO.setPrice(price);

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            ItemService itemService = new ItemService(conn);
            boolean added = itemService.addItem(itemDTO);

            if (added) {
                req.setAttribute("success", "Item added successfully.");
            } else {
                req.setAttribute("error", "Item already exists or failed to add.");
            }

            req.getRequestDispatcher("jsp/addItem.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Error adding item", e);
        }
    }
}

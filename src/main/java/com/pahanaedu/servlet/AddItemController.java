package com.pahanaedu.servlet;

import com.pahanaedu.business.item.dto.ItemDTO;
import com.pahanaedu.business.item.service.ItemService;
import com.pahanaedu.business.category.dto.CategoryDTO;
import com.pahanaedu.business.category.service.CategoryService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

@WebServlet("/addItem")
public class AddItemController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("doGet called for AddItemController");

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CategoryService categoryService = new CategoryService(conn);
            List<CategoryDTO> categories = categoryService.getAllCategories();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("jsp/addItem.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Failed to load categories", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name").trim();
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(name);
        itemDTO.setPrice(price);
        itemDTO.setCategoryId(categoryId);

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            ItemService itemService = new ItemService(conn);

            // Check if item name already exists
            if (itemService.isNameExists(name)) {
                req.setAttribute("error", "Item name already exists. Choose a different name.");
            } else {
                boolean added = itemService.addItem(itemDTO);
                if (added) {
                    req.setAttribute("success", "Item added successfully.");
                } else {
                    req.setAttribute("error", "Failed to add item.");
                }
            }

            // Reload categories for dropdown
            CategoryService categoryService = new CategoryService(conn);
            req.setAttribute("categories", categoryService.getAllCategories());

            req.getRequestDispatcher("jsp/addItem.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Error adding item", e);
        }
    }

}

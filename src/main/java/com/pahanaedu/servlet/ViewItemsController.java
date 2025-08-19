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
import java.sql.Connection;
import java.util.List;

@WebServlet("/viewItems")
public class ViewItemsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String categoryIdParam = req.getParameter("categoryId");
        int categoryId = categoryIdParam != null && !categoryIdParam.isEmpty() ? Integer.parseInt(categoryIdParam) : 0;

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            ItemService itemService = new ItemService(conn);
            List<ItemDTO> items;

            if (categoryId == 0) {
                items = itemService.getAllItems();
            } else {
                items = itemService.getItemsByCategory(categoryId);
            }

            CategoryService categoryService = new CategoryService(conn);
            List<CategoryDTO> categories = categoryService.getAllCategories();

            req.setAttribute("items", items);
            req.setAttribute("categories", categories);
            req.setAttribute("selectedCategoryId", categoryId);

            req.getRequestDispatcher("jsp/viewItems.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Failed to load items", e);
        }
    }
}

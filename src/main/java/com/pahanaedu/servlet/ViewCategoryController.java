package com.pahanaedu.servlet;

import com.pahanaedu.business.category.dto.CategoryDTO;
import com.pahanaedu.business.category.service.CategoryService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/viewCategories")
public class ViewCategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CategoryService service = new CategoryService(conn);
            List<CategoryDTO> categories = service.getAllCategories();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("jsp/viewCategories.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Failed to load categories", e);
        }
    }
}


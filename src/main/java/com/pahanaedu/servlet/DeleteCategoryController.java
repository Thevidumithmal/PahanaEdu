package com.pahanaedu.servlet;

import com.pahanaedu.business.category.service.CategoryService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/deleteCategory")
public class DeleteCategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CategoryService service = new CategoryService(conn);
            service.deleteCategory(id);
            resp.sendRedirect("viewCategories");
        } catch (Exception e) {
            throw new ServletException("Failed to delete category", e);
        }
    }
}


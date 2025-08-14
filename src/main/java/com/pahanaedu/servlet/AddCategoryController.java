package com.pahanaedu.servlet;

import com.pahanaedu.business.category.dto.CategoryDTO;
import com.pahanaedu.business.category.service.CategoryService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/addCategory")
public class AddCategoryController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        CategoryDTO dto = new CategoryDTO();
        dto.setName(name);

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CategoryService service = new CategoryService(conn);
            boolean added = service.addCategory(dto);
            req.setAttribute("success", added ? "Category added." : "Failed to add.");
            req.getRequestDispatcher("jsp/addCategory.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error adding category", e);
        }
    }
}

package com.pahanaedu.controller;

import com.pahanaedu.dto.CustomerDTO;
import com.pahanaedu.service.CustomerService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/addCustomer")
public class AddCustomerController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        CustomerDTO dto = new CustomerDTO();
        dto.setName(name);
        dto.setPhone(phone);
        dto.setAddress(address);

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CustomerService service = new CustomerService(conn);
            boolean success = service.addCustomer(dto);

            if (success) {
                resp.sendRedirect(req.getContextPath() + "/jsp/shopDashboard.jsp?msg=added");
            } else {
                resp.sendRedirect(req.getContextPath() + "/jsp/addCustomer.jsp?error=true");
            }
        } catch (Exception e) {
            throw new ServletException("Failed to add customer", e);
        }
    }
}

package com.pahanaedu.controller;


import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import com.pahanaedu.util.DBUtil;

@WebServlet("/addCustomer")
public class AddCustomerController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CustomerService service = new CustomerService(conn);

            Customer customer = new Customer();
            customer.setName(name);
            customer.setPhone(phone);
            customer.setAddress(address);

            boolean success = service.addCustomer(customer);
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




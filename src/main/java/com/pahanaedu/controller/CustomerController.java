package com.pahanaedu.controller;

import com.pahanaedu.model.Customer;
import com.pahanaedu.service.CustomerService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/customers")
public class CustomerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CustomerService service = new CustomerService(conn);

            if ("add".equals(action)) {
                req.getRequestDispatcher("jsp/addCustomer.jsp").forward(req, resp);
            } else if ("edit".equals(action)) {
                int accNum = Integer.parseInt(req.getParameter("accountNumber"));
                Customer c = service.getCustomerByAccountNumber(accNum);
                req.setAttribute("customer", c);
                req.getRequestDispatcher("jsp/editCustomer.jsp").forward(req, resp);
            } else if ("delete".equals(action)) {
                int accNum = Integer.parseInt(req.getParameter("accountNumber"));
                service.deleteCustomer(accNum);
                resp.sendRedirect("customers");
            } else {
                // Default: List all customers
                List<Customer> customers = service.getAllCustomers();
                req.setAttribute("customers", customers);
                req.getRequestDispatcher("jsp/viewCustomers.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            throw new ServletException("Error handling GET request", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CustomerService service = new CustomerService(conn);

            Customer c = new Customer.Builder()
                    .setAccountNumber(Integer.parseInt(req.getParameter("accountNumber")))
                    .setName(req.getParameter("name"))
                    .setAddress(req.getParameter("address"))
                    .setPhoneNumber(req.getParameter("phoneNumber"))
                    .setUnitsConsumed(Integer.parseInt(req.getParameter("unitsConsumed")))
                    .build();

            if ("add".equals(action)) {
                service.addCustomer(c);
            } else if ("edit".equals(action)) {
                service.updateCustomer(c);
            }

            resp.sendRedirect("customers");

        } catch (Exception e) {
            throw new ServletException("Error handling POST request", e);
        }
    }
}

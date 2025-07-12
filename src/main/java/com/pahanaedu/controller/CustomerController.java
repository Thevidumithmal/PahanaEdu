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
        try (Connection conn = DBUtil.getConnection()) {
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
                // default list all customers
                List<Customer> customers = service.getAllCustomers();
                req.setAttribute("customers", customers);
                req.getRequestDispatcher("jsp/viewCustomers.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try (Connection conn = DBUtil.getConnection()) {
            CustomerService service = new CustomerService(conn);

            if ("add".equals(action)) {
                Customer c = new Customer();
                c.setAccountNumber(Integer.parseInt(req.getParameter("accountNumber")));
                c.setName(req.getParameter("name"));
                c.setAddress(req.getParameter("address"));
                c.setPhoneNumber(req.getParameter("phoneNumber"));
                c.setUnitsConsumed(Integer.parseInt(req.getParameter("unitsConsumed")));

                service.addCustomer(c);
                resp.sendRedirect("customers");

            } else if ("edit".equals(action)) {
                Customer c = new Customer();
                c.setAccountNumber(Integer.parseInt(req.getParameter("accountNumber")));
                c.setName(req.getParameter("name"));
                c.setAddress(req.getParameter("address"));
                c.setPhoneNumber(req.getParameter("phoneNumber"));
                c.setUnitsConsumed(Integer.parseInt(req.getParameter("unitsConsumed")));

                service.updateCustomer(c);
                resp.sendRedirect("customers");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}


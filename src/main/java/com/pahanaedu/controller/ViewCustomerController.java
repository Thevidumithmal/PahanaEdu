package com.pahanaedu.controller;


import com.pahanaedu.dao.CustomerDao;
import com.pahanaedu.model.Customer;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/viewCustomer")
public class ViewCustomerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String phone = req.getParameter("phoneNumber");

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CustomerDao dao = new CustomerDao(conn);
            Customer customer = dao.getCustomersByPhone(phone);

            if (customer != null) {
                List<Customer> customers = new ArrayList<>();
                customers.add(customer);
                req.setAttribute("customers", customers);
            } else {
                req.setAttribute("notFound", "No customer found with that phone number.");
            }


            req.getRequestDispatcher("jsp/viewCustomers.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Error viewing customer", e);
        }
    }
}


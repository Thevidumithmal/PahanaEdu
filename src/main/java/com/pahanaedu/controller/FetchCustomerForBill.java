package com.pahanaedu.controller;

import com.pahanaedu.dao.CustomerDao;
import com.pahanaedu.dao.ItemDao;
import com.pahanaedu.model.Customer;
import com.pahanaedu.model.Item;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/fetchCustomerForBill")
public class FetchCustomerForBill extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String phone = req.getParameter("phoneNumber");

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CustomerDao customerDao = new CustomerDao(conn);
            ItemDao itemDao = new ItemDao(conn);

            Customer customer = customerDao.getCustomersByPhone(phone);
            List<Item> items = itemDao.getAllItems();

            if (customer != null && !items.isEmpty()) {
                req.setAttribute("customer", customer);
                req.setAttribute("items", items);
            } else {
                req.setAttribute("error", "No customer found with that phone number or no items available.");
            }

            req.getRequestDispatcher("jsp/bill.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Error fetching customer/item details", e);
        }
    }
}

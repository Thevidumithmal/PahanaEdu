package com.pahanaedu.controller;

import com.pahanaedu.dao.ItemDao;
import com.pahanaedu.dto.CustomerDTO;
import com.pahanaedu.mapper.CustomerMapper;
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
            Customer customerModel = new com.pahanaedu.dao.CustomerDao(conn).getCustomersByPhone(phone);
            ItemDao itemDao = new ItemDao(conn);
            List<Item> items = itemDao.getAllItems();

            if (customerModel != null && !items.isEmpty()) {
                CustomerDTO customerDto = CustomerMapper.toDTO(customerModel);
                req.setAttribute("customer", customerDto);
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

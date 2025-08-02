package com.pahanaedu.controller;

import com.pahanaedu.dto.CustomerDTO;
import com.pahanaedu.mapper.CustomerMapper;
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
            Customer customerModel = new com.pahanaedu.dao.CustomerDao(conn).getCustomersByPhone(phone);

            if (customerModel != null) {
                CustomerDTO customerDto = CustomerMapper.toDTO(customerModel);

                List<CustomerDTO> customers = new ArrayList<>();
                customers.add(customerDto);
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

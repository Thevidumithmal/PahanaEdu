package com.pahanaedu.servlet;

import com.pahanaedu.business.customer.dto.CustomerDTO;
import com.pahanaedu.business.customer.service.CustomerService;
import com.pahanaedu.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/editCustomer")
public class EditCustomerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendRedirect(req.getContextPath() + "/jsp/viewCustomers.jsp");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/jsp/viewCustomers.jsp");
            return;
        }

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CustomerService service = new CustomerService(conn);
            CustomerDTO customer = service.getCustomerById(id);
            if (customer == null) {
                req.setAttribute("error", "Customer not found");
                req.getRequestDispatcher("jsp/viewCustomers.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("jsp/editCustomer.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Failed to load customer for editing", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            String nicNo = req.getParameter("nicNo");
            String address = req.getParameter("address");
            String email = req.getParameter("email");

            boolean hasErrors = false;
            StringBuilder errorMsg = new StringBuilder();

            // Validate phone: exactly 10 digits (numbers only)
            if (phone == null || !phone.matches("\\d{10}")) {
                hasErrors = true;
                errorMsg.append("Phone must be exactly 10 digits (numbers only).<br>");
            }

            // Validate NIC: must be exactly 10 or 12 alphanumeric characters
            if (nicNo == null || !(nicNo.matches("[A-Za-z0-9]{10}") || nicNo.matches("[A-Za-z0-9]{12}"))) {
                hasErrors = true;
                errorMsg.append("NIC number must be exactly 10 or 12 characters (letters and/or numbers only).<br>");
            }

            if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                hasErrors = true;
                errorMsg.append("Invalid email format.<br>");
            }

            CustomerDTO dto = new CustomerDTO();
            dto.setId(id);
            dto.setName(name);
            dto.setPhone(phone);
            dto.setNicNo(nicNo);
            dto.setEmail(email);
            dto.setAddress(address);

            if (hasErrors) {
                req.setAttribute("validationErrors", errorMsg.toString());
                req.setAttribute("customer", dto);
                req.getRequestDispatcher("jsp/editCustomer.jsp").forward(req, resp);
                return;
            }

            try (Connection conn = DBUtil.getInstance().getConnection()) {
                CustomerService service = new CustomerService(conn);
                boolean updated = service.updateCustomer(dto);

                if (updated) {
                    resp.sendRedirect(req.getContextPath() + "/viewCustomer?phoneNumber=" + phone + "&msg=updated");
                } else {
                    req.setAttribute("error", "Update failed");
                    req.setAttribute("customer", dto);
                    req.getRequestDispatcher("jsp/editCustomer.jsp").forward(req, resp);
                }
            }
        } catch (Exception e) {
            throw new ServletException("Failed to update customer", e);
        }
    }
}

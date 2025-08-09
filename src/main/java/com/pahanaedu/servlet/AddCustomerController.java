package com.pahanaedu.servlet;

import com.pahanaedu.business.customer.dto.CustomerDTO;
import com.pahanaedu.business.customer.service.CustomerService;
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
        String nicNo = req.getParameter("nicNo");
        String address = req.getParameter("address");

        boolean hasErrors = false;
        StringBuilder errorMsg = new StringBuilder();

        // Validate phone: exactly 10 digits
        if (phone == null || !phone.matches("\\d{10}")) {
            hasErrors = true;
            errorMsg.append("Phone must be exactly 10 digits (numbers only).<br>");
        }

        // Validate NIC: must be 10 or 12 characters, letters or numbers only
        if (nicNo == null || !(nicNo.matches("[A-Za-z0-9]{10}") || nicNo.matches("[A-Za-z0-9]{12}"))) {
            hasErrors = true;
            errorMsg.append("NIC number must be exactly 10 or 12 characters (letters and/or numbers only).<br>");
        }

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CustomerService service = new CustomerService(conn);

            // Check if phone already exists in DB
            if (service.getCustomerByPhone(phone) != null) {
                hasErrors = true;
                errorMsg.append("This phone number already has a customer.<br>");
            }

            if (hasErrors) {
                req.setAttribute("validationErrors", errorMsg.toString());

                // Repopulate form fields
                req.setAttribute("name", name);
                req.setAttribute("phone", phone);
                req.setAttribute("nicNo", nicNo);
                req.setAttribute("address", address);

                // Forward back to JSP with errors
                req.getRequestDispatcher("/jsp/addCustomer.jsp").forward(req, resp);
                return;
            }

            // No errors, proceed to add customer
            CustomerDTO dto = new CustomerDTO();
            dto.setName(name);
            dto.setPhone(phone);
            dto.setNicNo(nicNo);
            dto.setAddress(address);

            boolean success = service.addCustomer(dto);

            if (success) {
                resp.sendRedirect(req.getContextPath() + "/jsp/addCustomer.jsp?msg=added");
            } else {
                resp.sendRedirect(req.getContextPath() + "/jsp/addCustomer.jsp?error=true");
            }

        } catch (Exception e) {
            throw new ServletException("Failed to add customer", e);
        }
    }
}

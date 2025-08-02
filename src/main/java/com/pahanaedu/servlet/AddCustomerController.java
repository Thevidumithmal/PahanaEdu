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
        String nicNo = req.getParameter("nicNo");  // new field
        String address = req.getParameter("address");

        CustomerDTO dto = new CustomerDTO();
        dto.setName(name);
        dto.setPhone(phone);
        dto.setNicNo(nicNo);
        dto.setAddress(address);

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CustomerService service = new CustomerService(conn);
            boolean success = service.addCustomer(dto);

            if (success) {
                // Redirect back to addCustomer.jsp with success message
                resp.sendRedirect(req.getContextPath() + "/jsp/addCustomer.jsp?msg=added");
            } else {
                // Redirect back with error flag
                resp.sendRedirect(req.getContextPath() + "/jsp/addCustomer.jsp?error=true");
            }
        } catch (Exception e) {
            throw new ServletException("Failed to add customer", e);
        }
    }
}

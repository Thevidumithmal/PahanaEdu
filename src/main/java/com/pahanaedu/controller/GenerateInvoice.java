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
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.*;

@WebServlet("/generateInvoiceCustomer")
public class GenerateInvoice extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String phoneNumber = req.getParameter("phoneNumber");
        List<Map<String, Object>> billItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            ItemDao itemDao = new ItemDao(conn);
            com.pahanaedu.dao.CustomerDao customerDao = new com.pahanaedu.dao.CustomerDao(conn);

            Customer customerModel = customerDao.getCustomersByPhone(phoneNumber);
            if (customerModel == null) {
                req.setAttribute("error", "No customer found for phone number: " + phoneNumber);
                req.getRequestDispatcher("jsp/bill.jsp").forward(req, resp);
                return;
            }

            // Convert to DTO
            CustomerDTO customerDto = CustomerMapper.toDTO(customerModel);

            List<Item> allItems = itemDao.getAllItems();
            for (Item item : allItems) {
                String qtyParam = req.getParameter("quantity_" + item.getId());
                if (qtyParam != null && !qtyParam.isEmpty()) {
                    int qty = Integer.parseInt(qtyParam);
                    if (qty > 0) {
                        BigDecimal subtotal = item.getPrice().multiply(BigDecimal.valueOf(qty));

                        Map<String, Object> billRow = new HashMap<>();
                        billRow.put("itemName", item.getName());
                        billRow.put("unitPrice", item.getPrice());
                        billRow.put("quantity", qty);
                        billRow.put("subtotal", subtotal);

                        billItems.add(billRow);
                        total = total.add(subtotal);
                    }
                }
            }

            if (billItems.isEmpty()) {
                req.setAttribute("error", "No items were selected.");
                req.getRequestDispatcher("jsp/bill.jsp").forward(req, resp);
                return;
            }

            HttpSession session = req.getSession();
            session.setAttribute("billItems", billItems);
            session.setAttribute("total", total);
            session.setAttribute("phoneNumber", phoneNumber);
            session.setAttribute("customer", customerDto); // Store DTO in session

            req.setAttribute("phoneNumber", phoneNumber);
            req.setAttribute("billItems", billItems);
            req.setAttribute("total", total);
            req.setAttribute("customer", customerDto);  // Pass DTO to JSP
            req.setAttribute("customerName", customerDto.getName());

            req.getRequestDispatcher("jsp/invoicePreview.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Invoice generation failed", e);
        }
    }
}

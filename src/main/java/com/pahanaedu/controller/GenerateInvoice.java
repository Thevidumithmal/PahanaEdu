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
            CustomerDao customerDao = new CustomerDao(conn); // ✅ Add this

            // ✅ Fetch customer from DB
            Customer customer = customerDao.getCustomersByPhone(phoneNumber);
            if (customer == null) {
                req.setAttribute("error", "No customer found for phone number: " + phoneNumber);
                req.getRequestDispatcher("jsp/bill.jsp").forward(req, resp);
                return;
            }

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

            // ✅ Store in session for future use
            HttpSession session = req.getSession();
            session.setAttribute("billItems", billItems);
            session.setAttribute("total", total);
            session.setAttribute("phoneNumber", phoneNumber);
            session.setAttribute("customer", customer); // ✅ Store full customer object

            req.setAttribute("phoneNumber", phoneNumber);
            req.setAttribute("billItems", billItems);
            req.setAttribute("total", total);
            req.setAttribute("customer", customer); // ✅ Pass to JSP
            req.getRequestDispatcher("jsp/invoicePreview.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Invoice generation failed", e);
        }
    }
}


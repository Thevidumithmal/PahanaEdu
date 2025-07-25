package com.pahanaedu.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

import com.pahanaedu.util.DBUtil;

@WebServlet("/saveInvoice")
public class SaveInvoiceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String phoneNumber = request.getParameter("phoneNumber");
        List<Map<String, Object>> billItems = (List<Map<String, Object>>) request.getSession().getAttribute("billItems");
        BigDecimal total = (BigDecimal) request.getSession().getAttribute("total");

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            // Step 1: Insert invoice main record
            String insertInvoiceSql = "INSERT INTO invoices (phone_number, invoice_date, total) VALUES (?, NOW(), ?)";
            PreparedStatement invoiceStmt = conn.prepareStatement(insertInvoiceSql, Statement.RETURN_GENERATED_KEYS);
            invoiceStmt.setString(1, phoneNumber);
            invoiceStmt.setBigDecimal(2, total);
            invoiceStmt.executeUpdate();

            ResultSet rs = invoiceStmt.getGeneratedKeys();
            int invoiceId = 0;
            if (rs.next()) {
                invoiceId = rs.getInt(1);
            }

            // Step 2: Insert each item into invoice_items
            String insertItemSql = "INSERT INTO invoice_items (invoice_id, item_name, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement itemStmt = conn.prepareStatement(insertItemSql);

            for (Map<String, Object> item : billItems) {
                itemStmt.setInt(1, invoiceId);
                itemStmt.setString(2, (String) item.get("itemName"));
                itemStmt.setInt(3, (Integer) item.get("quantity"));
                itemStmt.setBigDecimal(4, (BigDecimal) item.get("unitPrice"));
                itemStmt.setBigDecimal(5, (BigDecimal) item.get("subtotal"));
                itemStmt.addBatch();
            }
            itemStmt.executeBatch();

            conn.commit();

            request.setAttribute("success", "✅ Invoice saved successfully.");
            request.getRequestDispatcher("jsp/shopDashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Error saving invoice.");
            request.getRequestDispatcher("jsp/invoicePreview.jsp").forward(request, response);
        }
    }
}

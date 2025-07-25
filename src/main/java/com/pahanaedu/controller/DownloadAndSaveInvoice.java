package com.pahanaedu.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.List;

import com.pahanaedu.util.DBUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@WebServlet("/downloadAndSaveInvoice")
public class DownloadAndSaveInvoice extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String phoneNumber = request.getParameter("phoneNumber");
        List<Map<String, Object>> billItems = (List<Map<String, Object>>) request.getSession().getAttribute("billItems");
        BigDecimal total = (BigDecimal) request.getSession().getAttribute("total");

        int invoiceId = 0;

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            // Save invoice
            String insertInvoiceSql = "INSERT INTO invoices (phone_number, invoice_date, total) VALUES (?, NOW(), ?)";
            PreparedStatement invoiceStmt = conn.prepareStatement(insertInvoiceSql, Statement.RETURN_GENERATED_KEYS);
            invoiceStmt.setString(1, phoneNumber);
            invoiceStmt.setBigDecimal(2, total);
            invoiceStmt.executeUpdate();

            ResultSet rs = invoiceStmt.getGeneratedKeys();
            if (rs.next()) {
                invoiceId = rs.getInt(1);
            }

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

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error saving invoice.");
            request.getRequestDispatcher("jsp/invoicePreview.jsp").forward(request, response);
            return;
        }

        // If saved, generate PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=invoice_" + invoiceId + ".pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            document.add(new Paragraph("Invoice"));
            document.add(new Paragraph("Phone Number: " + phoneNumber));
            document.add(new Paragraph("Invoice ID: " + invoiceId));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.addCell("Item Name");
            table.addCell("Quantity");
            table.addCell("Unit Price");
            table.addCell("Subtotal");

            for (Map<String, Object> item : billItems) {
                table.addCell((String) item.get("itemName"));
                table.addCell(item.get("quantity").toString());
                table.addCell(item.get("unitPrice").toString());
                table.addCell(item.get("subtotal").toString());
            }

            document.add(table);
            document.add(new Paragraph("Total: " + total));
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

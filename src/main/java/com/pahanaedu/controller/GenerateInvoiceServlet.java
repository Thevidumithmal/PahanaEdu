package com.pahanaedu.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
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
import java.util.List;

@WebServlet("/generateInvoice")
public class GenerateInvoiceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String phoneNumber = req.getParameter("phoneNumber");

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            CustomerDao customerDao = new CustomerDao(conn);
            ItemDao itemDao = new ItemDao(conn);

            List<Customer> customerList = customerDao.getCustomersByPhone(phoneNumber);
            Customer customer = customerList.isEmpty() ? null : customerList.get(0);
            List<Item> allItems = itemDao.getAllItems();

            // Prepare invoice items
            List<Item> purchasedItems = new ArrayList<>();
            Map<String, Integer> quantities = new HashMap<>();
            BigDecimal totalPrice = BigDecimal.ZERO;

            for (Item item : allItems) {
                String quantityStr = req.getParameter("quantity_" + item.getId());
                if (quantityStr != null && !quantityStr.trim().isEmpty()) {
                    int qty = Integer.parseInt(quantityStr);
                    if (qty > 0) {
                        purchasedItems.add(item);
                        quantities.put(item.getName(), qty);

                        BigDecimal subtotal = item.getPrice().multiply(BigDecimal.valueOf(qty));
                        totalPrice = totalPrice.add(subtotal);
                    }
                }
            }


            // Generate PDF
            Document document = new Document();
            resp.setContentType("application/pdf");
            resp.setHeader("Content-Disposition", "attachment; filename=invoice.pdf");
            PdfWriter.getInstance(document, resp.getOutputStream());

            document.open();

            Font bold = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            document.add(new Paragraph("INVOICE", bold));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Customer Name: " + customer.getName()));
            document.add(new Paragraph("Phone Number: " + customer.getPhone()));
            document.add(new Paragraph("Address: " + customer.getAddress()));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{4, 2, 2, 2});

            table.addCell("Item");
            table.addCell("Unit Price");
            table.addCell("Quantity");
            table.addCell("Total");

            for (Item item : purchasedItems) {
                int qty = quantities.get(item.getName());
                BigDecimal subtotal = item.getPrice().multiply(BigDecimal.valueOf(qty));

                table.addCell(item.getName());
                table.addCell(String.format("%.2f", item.getPrice()));
                table.addCell(String.valueOf(qty));
                table.addCell(String.format("%.2f", subtotal));
            }

            PdfPCell totalCell = new PdfPCell(new Phrase("Total", bold));
            totalCell.setColspan(3);
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(totalCell);
            table.addCell(String.format("%.2f", totalPrice));

            document.add(table);
            document.close();

        } catch (Exception e) {
            throw new ServletException("Error generating PDF", e);
        }
    }
}

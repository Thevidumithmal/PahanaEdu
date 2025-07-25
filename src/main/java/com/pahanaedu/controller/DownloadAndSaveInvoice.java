package com.pahanaedu.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.pahanaedu.dao.InvoiceDao;
import com.pahanaedu.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.pahanaedu.model.InvoiceItem;
import com.pahanaedu.model.Invoice;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@WebServlet("/downloadAndSaveInvoice")
public class DownloadAndSaveInvoice extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String phoneNumber = request.getParameter("phoneNumber");
        String customerName = request.getParameter("customerName");

        List<Map<String, Object>> billItems = (List<Map<String, Object>>) request.getSession().getAttribute("billItems");
        BigDecimal total = (BigDecimal) request.getSession().getAttribute("total");
        Invoice invoice = new Invoice(customerName, phoneNumber, total);

        int invoiceId;

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            InvoiceDao invoiceDao = new InvoiceDao(conn);
            invoiceId = invoiceDao.saveInvoice(invoice);
            List<InvoiceItem> invoiceItemList = billItems.stream().map(item -> {
                InvoiceItem invoiceItem = new InvoiceItem();
                invoiceItem.setItemName((String) item.get("itemName"));
                invoiceItem.setQuantity((Integer) item.get("quantity"));
                invoiceItem.setUnitPrice((BigDecimal) item.get("unitPrice"));
                invoiceItem.setSubtotal((BigDecimal) item.get("subtotal"));
                return invoiceItem;
            }).toList();

            invoiceDao.saveInvoiceItems(invoiceId, invoiceItemList);


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

            String logoPath = getServletContext().getRealPath("/image/logo.jpg");
            Image logo = Image.getInstance(logoPath);
            logo.scaleAbsolute(80, 80);
            logo.setAlignment(Image.ALIGN_CENTER);
            document.add(logo);

            Paragraph header = new Paragraph("📚 PahanaEdu Book Shop", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLUE));
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            Paragraph address = new Paragraph("No. 123, Main Street, Colombo 01, Sri Lanka", FontFactory.getFont(FontFactory.HELVETICA, 12));
            address.setAlignment(Element.ALIGN_CENTER);
            document.add(address);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("🧾 Invoice", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Customer Name: " + customerName));
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

            document.add(new Paragraph(" "));
            Paragraph footer = new Paragraph("🛒 Thank you for shopping with us!", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12, BaseColor.DARK_GRAY));
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

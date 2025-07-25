package com.pahanaedu.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@WebServlet("/downloadInvoicePdf")
public class DownloadInvoicePdf extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        List<Map<String, Object>> billItems = (List<Map<String, Object>>) session.getAttribute("billItems");
        BigDecimal total = (BigDecimal) session.getAttribute("total");

        String phoneNumber = (String) session.getAttribute("phoneNumber");

        if (billItems == null || total == null || phoneNumber == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing invoice data.");
            return;
        }

        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "attachment; filename=invoice.pdf");

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, resp.getOutputStream());
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font tableFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            document.add(new Paragraph("Invoice", titleFont));
            document.add(new Paragraph("Phone Number: " + phoneNumber));
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{3, 2, 2, 2});
            table.setSpacingBefore(10f);

            // Table Headers
            Stream.of("Item", "Unit Price", "Qty", "Subtotal").forEach(header -> {
                PdfPCell cell = new PdfPCell(new Phrase(header, tableFont));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
            });

            // Table Data
            for (Map<String, Object> item : billItems) {
                table.addCell(item.get("itemName").toString());
                table.addCell(String.format("%.2f", item.get("unitPrice")));
                table.addCell(item.get("quantity").toString());
                table.addCell(String.format("%.2f", item.get("subtotal")));
            }

            // Total Row
            PdfPCell totalCell = new PdfPCell(new Phrase("Total", tableFont));
            totalCell.setColspan(3);
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(totalCell);
            table.addCell(String.format("%.2f", total.doubleValue()));


            document.add(table);
            document.close();

        } catch (DocumentException e) {
            throw new ServletException("Error generating PDF", e);
        }
    }
}

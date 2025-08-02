package com.pahanaedu.servlet;

import com.pahanaedu.business.invoice.dto.InvoiceDTO;
import com.pahanaedu.business.invioceItem.dto.InvoiceItemDTO;
import com.pahanaedu.business.invoice.service.InvoiceService;
import com.pahanaedu.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/downloadAndSaveInvoice")
public class DownloadAndSaveInvoice extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String phoneNumber = request.getParameter("phoneNumber");
        String customerName = request.getParameter("customerName");

        List<Map<String, Object>> billItems = (List<Map<String, Object>>) request.getSession().getAttribute("billItems");
        BigDecimal total = (BigDecimal) request.getSession().getAttribute("total");

        // Convert session billItems to List<InvoiceItemDTO>
        List<InvoiceItemDTO> invoiceItemDTOs = billItems.stream().map(item -> {
            InvoiceItemDTO dto = new InvoiceItemDTO();
            dto.setItemName((String) item.get("itemName"));
            dto.setQuantity((Integer) item.get("quantity"));
            dto.setUnitPrice((BigDecimal) item.get("unitPrice"));
            dto.setSubtotal((BigDecimal) item.get("subtotal"));
            return dto;
        }).collect(Collectors.toList());

        // Create InvoiceDTO
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setCustomerName(customerName);
        invoiceDTO.setPhoneNumber(phoneNumber);
        invoiceDTO.setTotal(total);
        invoiceDTO.setCreatedAt(new Date());

        int invoiceId;

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            InvoiceService invoiceService = new InvoiceService(conn);
            invoiceId = invoiceService.saveInvoice(invoiceDTO, invoiceItemDTOs);

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error saving invoice.");
            request.getRequestDispatcher("jsp/invoicePreview.jsp").forward(request, response);
            return;
        }

        // Generate PDF response (same as before)
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

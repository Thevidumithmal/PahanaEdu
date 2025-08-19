package com.pahanaedu.servlet;

import com.pahanaedu.business.invoice.dto.InvoiceDTO;
import com.pahanaedu.business.invioceItem.dto.InvoiceItemDTO;
import com.pahanaedu.business.invoice.service.InvoiceService;
import com.pahanaedu.util.DBUtil;
import com.pahanaedu.util.EmailUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        if (billItems == null || billItems.isEmpty()) {
            request.setAttribute("error", "No items found for invoice.");
            request.getRequestDispatcher("jsp/invoicePreview.jsp").forward(request, response);
            return;
        }

        // Fetch customer email from DB
        String customerEmail = null;
        try (Connection conn = DBUtil.getInstance().getConnection()) {
            String sql = "SELECT email FROM customers WHERE phone = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, phoneNumber);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    customerEmail = rs.getString("email");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        // Generate PDF into byte array
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            // Add logo
            String logoPath = getServletContext().getRealPath("/image/logo.jpg");
            try {
                Image logo = Image.getInstance(logoPath);
                logo.scaleAbsolute(80, 80);
                logo.setAlignment(Image.ALIGN_CENTER);
                document.add(logo);
            } catch (Exception ignored) {}

            // Header
            Paragraph header = new Paragraph("📚 PahanaEdu Book Shop",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLUE));
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);

            Paragraph address = new Paragraph("No. 123, Main Street, Colombo 01, Sri Lanka",
                    FontFactory.getFont(FontFactory.HELVETICA, 12));
            address.setAlignment(Element.ALIGN_CENTER);
            document.add(address);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("🧾 Invoice", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            document.add(new Paragraph("Customer Name: " + customerName));
            document.add(new Paragraph("Phone Number: " + phoneNumber));
            document.add(new Paragraph("Invoice ID: " + invoiceId));
            document.add(new Paragraph(" "));

            // Table
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
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

            PdfPCell totalCell = new PdfPCell(new Phrase("Total: " + total));
            totalCell.setColspan(4);
            totalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(totalCell);

            document.add(table);

            Paragraph footer = new Paragraph("🛒 Thank you for shopping with us!",
                    FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12, BaseColor.DARK_GRAY));
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();

            // Send email with attachment
            if (customerEmail != null && !customerEmail.isEmpty()) {
                try {
                    EmailUtil.sendEmailWithAttachment(
                            customerEmail,
                            "PahanaEdu Bookshop",
                            "Dear " + customerName + ",\n\nPlease find attached your invoice.\n\nThank you Shopping Us!\n PahanaEdu - Panadura",
                            baos.toByteArray(),
                            "invoice_" + invoiceId + ".pdf"
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // Send PDF to browser
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=invoice_" + invoiceId + ".pdf");
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

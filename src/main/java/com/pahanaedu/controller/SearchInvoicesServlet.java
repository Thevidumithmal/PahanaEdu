package com.pahanaedu.controller;

import com.pahanaedu.dao.InvoiceDao;
import com.pahanaedu.model.Invoice;
import com.pahanaedu.model.InvoiceItem;
import com.pahanaedu.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/searchInvoices")
public class SearchInvoicesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String phoneNumber = req.getParameter("phoneNumber");
        if (phoneNumber == null || phoneNumber.isBlank()) {
            req.setAttribute("error", "Please enter a valid phone number.");
            req.getRequestDispatcher("jsp/searchInvoices.jsp").forward(req, resp);
            return;
        }

        try (Connection conn = DBUtil.getInstance().getConnection()) {
            InvoiceDao invoiceDao = new InvoiceDao(conn);

            // Fetch invoices by phone number
            List<Invoice> invoices = invoiceDao.getInvoicesByPhoneNumber(phoneNumber);

            if (invoices.isEmpty()) {
                req.setAttribute("error", "No invoices found for phone number: " + phoneNumber);
                req.getRequestDispatcher("jsp/searchInvoices.jsp").forward(req, resp);
                return;
            }

            // For each invoice, fetch items and add to a map
            Map<Integer, List<InvoiceItem>> invoiceItemsMap = new HashMap<>();
            for (Invoice invoice : invoices) {
                List<InvoiceItem> items = invoiceDao.getInvoiceItemsByInvoiceId(invoice.getId());
                invoiceItemsMap.put(invoice.getId(), items);
            }

            req.setAttribute("invoices", invoices);
            req.setAttribute("invoiceItemsMap", invoiceItemsMap);
            req.getRequestDispatcher("jsp/showInvoices.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Error fetching invoices", e);
        }
    }
}


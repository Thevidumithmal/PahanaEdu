package com.pahanaedu.dao;

import com.pahanaedu.model.Invoice;
import com.pahanaedu.model.InvoiceItem;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class InvoiceDao {
    private final Connection connection;

    public InvoiceDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Saves the invoice to the database and returns the generated invoice ID.
     */
    public int saveInvoice(Invoice invoice) throws SQLException {
        String sql = "INSERT INTO invoices (customer_name, phone_number, invoice_date, total) VALUES (?, ?, NOW(), ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, invoice.getCustomerName());
            stmt.setString(2, invoice.getPhoneNumber());
            stmt.setBigDecimal(3, invoice.getTotal());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    invoice.setId(generatedId);  // set generated id back to model if you want
                    return generatedId;
                }
            }
        }
        throw new SQLException("Failed to create invoice: No ID returned.");
    }


    /**
     * Saves all invoice items for a given invoice ID.
     */
    public void saveInvoiceItems(int invoiceId, List<InvoiceItem> items) throws SQLException {
        String sql = "INSERT INTO invoice_items (invoice_id, item_name, quantity, unit_price, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (InvoiceItem item : items) {
                stmt.setInt(1, invoiceId);
                stmt.setString(2, item.getItemName());
                stmt.setInt(3, item.getQuantity());
                stmt.setBigDecimal(4, item.getUnitPrice());
                stmt.setBigDecimal(5, item.getSubtotal());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }
}

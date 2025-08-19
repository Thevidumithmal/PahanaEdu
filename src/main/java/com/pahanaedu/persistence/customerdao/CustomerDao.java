package com.pahanaedu.persistence.customerdao;

import com.pahanaedu.business.customer.model.Customer;

import java.sql.*;

public class CustomerDao {
    private final Connection connection;

    public CustomerDao(Connection connection) {
        this.connection = connection;
    }

    // Add a customer (include email)
    public boolean addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (name, phone, address, nic_no, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getNicNo());
            stmt.setString(5, customer.getEmail()); // email column
            return stmt.executeUpdate() > 0;
        }
    }

    // Get customer by phone (include email)
    public Customer getCustomersByPhone(String phone) throws SQLException {
        String sql = "SELECT * FROM customers WHERE phone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setNicNo(rs.getString("nic_no"));
                customer.setEmail(rs.getString("email")); // email column
                return customer;
            }
        }
        return null;
    }

    // Get customer by ID (include email)
    public Customer getCustomerById(int id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customer.setNicNo(rs.getString("nic_no"));
                customer.setEmail(rs.getString("email")); // email column
                return customer;
            }
        }
        return null;
    }

    // Update customer (include email)
    public boolean updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET name = ?, phone = ?, address = ?, nic_no = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getNicNo());
            stmt.setString(5, customer.getEmail()); // email column
            stmt.setInt(6, customer.getId());
            return stmt.executeUpdate() > 0;
        }
    }
}

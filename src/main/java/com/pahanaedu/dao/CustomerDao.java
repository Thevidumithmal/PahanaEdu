package com.pahanaedu.dao;

import com.pahanaedu.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private final Connection connection;

    public CustomerDao(Connection connection) {
        this.connection = connection;
    }

    public boolean addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (name, phone, address) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getAddress());
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Customer> getCustomersByPhone(String phone) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE phone = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
        }
        return customers;
    }

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
                return customer;
            }
        }
        return null;
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET name = ?, phone = ?, address = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getAddress());
            stmt.setInt(4, customer.getId());
            return stmt.executeUpdate() > 0;
        }
    }
}

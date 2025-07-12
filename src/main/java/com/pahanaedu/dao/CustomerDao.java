package com.pahanaedu.dao;

import com.pahanaedu.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private Connection connection;

    public CustomerDao(Connection connection) {
        this.connection = connection;
    }

    public boolean addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (accountNumber, name, address, phoneNumber, unitsConsumed) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, customer.getAccountNumber());
        stmt.setString(2, customer.getName());
        stmt.setString(3, customer.getAddress());
        stmt.setString(4, customer.getPhoneNumber());
        stmt.setInt(5, customer.getUnitsConsumed());
        return stmt.executeUpdate() > 0;
    }

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Customer c = new Customer();
            c.setAccountNumber(rs.getInt("accountNumber"));
            c.setName(rs.getString("name"));
            c.setAddress(rs.getString("address"));
            c.setPhoneNumber(rs.getString("phoneNumber"));
            c.setUnitsConsumed(rs.getInt("unitsConsumed"));
            list.add(c);
        }
        return list;
    }

    public Customer getCustomerByAccountNumber(int accountNumber) throws SQLException {
        String sql = "SELECT * FROM customers WHERE accountNumber = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, accountNumber);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Customer c = new Customer();
            c.setAccountNumber(rs.getInt("accountNumber"));
            c.setName(rs.getString("name"));
            c.setAddress(rs.getString("address"));
            c.setPhoneNumber(rs.getString("phoneNumber"));
            c.setUnitsConsumed(rs.getInt("unitsConsumed"));
            return c;
        }
        return null;
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET name = ?, address = ?, phoneNumber = ?, unitsConsumed = ? WHERE accountNumber = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, customer.getName());
        stmt.setString(2, customer.getAddress());
        stmt.setString(3, customer.getPhoneNumber());
        stmt.setInt(4, customer.getUnitsConsumed());
        stmt.setInt(5, customer.getAccountNumber());
        return stmt.executeUpdate() > 0;
    }

    public boolean deleteCustomer(int accountNumber) throws SQLException {
        String sql = "DELETE FROM customers WHERE accountNumber = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, accountNumber);
        return stmt.executeUpdate() > 0;
    }
}


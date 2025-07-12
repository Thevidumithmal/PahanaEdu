package com.pahanaedu.service;

import com.pahanaedu.dao.CustomerDao;
import com.pahanaedu.model.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private CustomerDao customerDao;

    public CustomerService(Connection connection) {
        this.customerDao = new CustomerDao(connection);
    }

    public boolean addCustomer(Customer customer) throws SQLException {
        return customerDao.addCustomer(customer);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customerDao.getAllCustomers();
    }

    public Customer getCustomerByAccountNumber(int accountNumber) throws SQLException {
        return customerDao.getCustomerByAccountNumber(accountNumber);
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        return customerDao.updateCustomer(customer);
    }

    public boolean deleteCustomer(int accountNumber) throws SQLException {
        return customerDao.deleteCustomer(accountNumber);
    }
}

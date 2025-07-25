package com.pahanaedu.service;

import com.pahanaedu.dao.CustomerDao;
import com.pahanaedu.model.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService(Connection connection) {
        this.customerDao = new CustomerDao(connection);
    }

    public boolean addCustomer(Customer customer) throws SQLException {
        return customerDao.addCustomer(customer);
    }

    public Customer getCustomerByPhone(String phone) throws SQLException {
        return customerDao.getCustomersByPhone(phone);
    }

    public Customer getCustomerById(int id) throws SQLException {
        return customerDao.getCustomerById(id);
    }

    public boolean updateCustomer(Customer customer) throws SQLException {
        return customerDao.updateCustomer(customer);
    }
}

package com.pahanaedu.business.customer.service;

import com.pahanaedu.persistence.customerdao.CustomerDao;
import com.pahanaedu.business.customer.model.Customer;
import com.pahanaedu.business.customer.dto.CustomerDTO;
import com.pahanaedu.business.customer.mapper.CustomerMapper;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerService {
    private final CustomerDao customerDao;

    public CustomerService(Connection connection) {
        this.customerDao = new CustomerDao(connection);
    }

    public boolean addCustomer(CustomerDTO dto) throws SQLException {
        Customer customer = CustomerMapper.toModel(dto);
        return customerDao.addCustomer(customer);
    }

    public CustomerDTO getCustomerByPhone(String phone) throws SQLException {
        Customer customer = customerDao.getCustomersByPhone(phone);
        return CustomerMapper.toDTO(customer);
    }

    public CustomerDTO getCustomerById(int id) throws SQLException {
        Customer customer = customerDao.getCustomerById(id);
        return CustomerMapper.toDTO(customer);
    }

    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        Customer customer = CustomerMapper.toModel(dto);
        return customerDao.updateCustomer(customer);
    }
}

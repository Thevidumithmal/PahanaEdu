package com.pahanaedu.mapper;

import com.pahanaedu.model.Customer;
import com.pahanaedu.dto.CustomerDTO;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) return null;
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setPhone(customer.getPhone());
        dto.setAddress(customer.getAddress());
        return dto;
    }

    public static Customer toModel(CustomerDTO dto) {
        if (dto == null) return null;
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        return customer;
    }
}

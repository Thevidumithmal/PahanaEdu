package com.pahanaedu.business.customer.mapper;

import com.pahanaedu.business.customer.model.Customer;
import com.pahanaedu.business.customer.dto.CustomerDTO;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) return null;
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setPhone(customer.getPhone());
        dto.setAddress(customer.getAddress());
        dto.setNicNo(customer.getNicNo());  // NEW FIELD
        return dto;
    }

    public static Customer toModel(CustomerDTO dto) {
        if (dto == null) return null;
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setPhone(dto.getPhone());
        customer.setAddress(dto.getAddress());
        customer.setNicNo(dto.getNicNo());  // NEW FIELD
        return customer;
    }
}

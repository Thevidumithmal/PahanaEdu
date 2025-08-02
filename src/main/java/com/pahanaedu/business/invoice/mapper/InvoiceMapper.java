package com.pahanaedu.business.invoice.mapper;

import com.pahanaedu.business.invoice.dto.InvoiceDTO;
import com.pahanaedu.business.invoice.model.Invoice;

public class InvoiceMapper {
    public static InvoiceDTO toDTO(Invoice invoice) {
        InvoiceDTO dto = new InvoiceDTO();
        dto.setId(invoice.getId());
        dto.setCustomerName(invoice.getCustomerName());
        dto.setPhoneNumber(invoice.getPhoneNumber());
        dto.setTotal(invoice.getTotal());
        dto.setCreatedAt(invoice.getCreatedAt());
        return dto;
    }

    public static Invoice toEntity(InvoiceDTO dto) {
        Invoice invoice = new Invoice();
        invoice.setId(dto.getId());
        invoice.setCustomerName(dto.getCustomerName());
        invoice.setPhoneNumber(dto.getPhoneNumber());
        invoice.setTotal(dto.getTotal());
        invoice.setCreatedAt(dto.getCreatedAt());
        return invoice;
    }
}

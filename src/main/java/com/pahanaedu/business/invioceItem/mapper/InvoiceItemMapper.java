package com.pahanaedu.business.invioceItem.mapper;

import com.pahanaedu.business.invioceItem.dto.InvoiceItemDTO;
import com.pahanaedu.business.invioceItem.model.InvoiceItem;

public class InvoiceItemMapper {
    public static InvoiceItemDTO toDTO(InvoiceItem item) {
        InvoiceItemDTO dto = new InvoiceItemDTO();
        dto.setItemName(item.getItemName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getUnitPrice());
        dto.setSubtotal(item.getSubtotal());
        return dto;
    }

    public static InvoiceItem toEntity(InvoiceItemDTO dto) {
        InvoiceItem item = new InvoiceItem();
        item.setItemName(dto.getItemName());
        item.setQuantity(dto.getQuantity());
        item.setUnitPrice(dto.getUnitPrice());
        item.setSubtotal(dto.getSubtotal());
        return item;
    }
}

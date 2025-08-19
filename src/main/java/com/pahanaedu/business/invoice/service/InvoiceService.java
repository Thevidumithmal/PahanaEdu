package com.pahanaedu.business.invoice.service;

import com.pahanaedu.persistence.invoicedao.InvoiceDao;
import com.pahanaedu.business.invoice.dto.InvoiceDTO;
import com.pahanaedu.business.invioceItem.dto.InvoiceItemDTO;
import com.pahanaedu.business.invioceItem.mapper.InvoiceItemMapper;
import com.pahanaedu.business.invoice.mapper.InvoiceMapper;
import com.pahanaedu.business.invoice.model.Invoice;
import com.pahanaedu.business.invioceItem.model.InvoiceItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceService {
    private final InvoiceDao invoiceDao;

    public InvoiceService(Connection conn) {
        this.invoiceDao = new InvoiceDao(conn);
    }

    public int saveInvoice(InvoiceDTO invoiceDTO, List<InvoiceItemDTO> itemDTOs) throws SQLException {
        Invoice invoice = InvoiceMapper.toEntity(invoiceDTO);
        int invoiceId = invoiceDao.saveInvoice(invoice);

        List<InvoiceItem> items = itemDTOs.stream()
                .map(InvoiceItemMapper::toEntity)
                .collect(Collectors.toList());

        invoiceDao.saveInvoiceItems(invoiceId, items);
        return invoiceId;
    }

    public List<InvoiceDTO> getInvoicesByPhone(String phoneNumber) throws SQLException {
        return invoiceDao.getInvoicesByPhoneNumber(phoneNumber)
                .stream()
                .map(InvoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<InvoiceItemDTO> getItemsByInvoiceId(int invoiceId) throws SQLException {
        return invoiceDao.getInvoiceItemsByInvoiceId(invoiceId)
                .stream()
                .map(InvoiceItemMapper::toDTO)
                .collect(Collectors.toList());
    }
}

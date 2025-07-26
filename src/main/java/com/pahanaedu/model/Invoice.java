package com.pahanaedu.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class Invoice {
    private int id; // optional, for DB generated ID
    private String customerName;
    private String phoneNumber;
    private BigDecimal total;
    private Date createdAt;   // add this for invoice date/time

    public Invoice() {}

    public Invoice(String customerName, String phoneNumber, BigDecimal total, Date createdAt) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.total = total;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

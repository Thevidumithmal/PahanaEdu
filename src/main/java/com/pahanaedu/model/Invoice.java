package com.pahanaedu.model;

import java.math.BigDecimal;

public class Invoice {
    private int id; // optional, for DB generated ID
    private String customerName;
    private String phoneNumber;
    private BigDecimal total;

    public Invoice() {}

    public Invoice(String customerName, String phoneNumber, BigDecimal total) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.total = total;
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
}


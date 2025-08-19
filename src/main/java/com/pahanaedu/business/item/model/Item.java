package com.pahanaedu.business.item.model;

import java.math.BigDecimal;

public class Item {
    private int id;
    private String name;
    private BigDecimal price;
    private int categoryId; // new field for category reference

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}

package com.pahanaedu.business.item.dto;

import java.math.BigDecimal;

public class ItemDTO {
    private int id;
    private String name;
    private BigDecimal price;
    private int categoryId; // category reference

    public ItemDTO() {}

    public ItemDTO(int id, String name, BigDecimal price, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}

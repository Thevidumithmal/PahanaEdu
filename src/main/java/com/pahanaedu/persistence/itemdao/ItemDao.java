package com.pahanaedu.persistence.itemdao;

import com.pahanaedu.business.item.model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {
    private final Connection connection;

    public ItemDao(Connection connection) { this.connection = connection; }

    public boolean addItem(Item item) throws SQLException {
        String sql = "INSERT INTO items (name, price, category_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getName());
            stmt.setBigDecimal(2, item.getPrice());
            stmt.setInt(3, item.getCategoryId());
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Item> getAllItems() throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setCategoryId(rs.getInt("category_id"));
                items.add(item);
            }
        }
        return items;
    }

    public Item getItemById(int id) throws SQLException {
        String sql = "SELECT * FROM items WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Item item = new Item();
                    item.setId(rs.getInt("id"));
                    item.setName(rs.getString("name"));
                    item.setPrice(rs.getBigDecimal("price"));
                    item.setCategoryId(rs.getInt("category_id"));
                    return item;
                }
            }
        }
        return null;
    }

    public boolean updateItem(Item item) throws SQLException {
        String sql = "UPDATE items SET name=?, price=?, category_id=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getName());
            stmt.setBigDecimal(2, item.getPrice());
            stmt.setInt(3, item.getCategoryId());
            stmt.setInt(4, item.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteItem(int id) throws SQLException {
        String sql = "DELETE FROM items WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Item> getItemsByCategory(int categoryId) throws SQLException {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE category_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item();
                    item.setId(rs.getInt("id"));
                    item.setName(rs.getString("name"));
                    item.setPrice(rs.getBigDecimal("price"));
                    item.setCategoryId(rs.getInt("category_id"));
                    items.add(item);
                }
            }
        }
        return items;
    }

    public boolean isNameExists(String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM items WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name.trim());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

}

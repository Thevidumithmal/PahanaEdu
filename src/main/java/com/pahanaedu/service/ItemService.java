package com.pahanaedu.service;

import com.pahanaedu.dao.ItemDao;
import com.pahanaedu.model.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemService {
    private final ItemDao itemDao;

    public ItemService(Connection connection) {
        this.itemDao = new ItemDao(connection);
    }

    public boolean addItem(Item item) throws SQLException {
        return itemDao.addItem(item);
    }

    public List<Item> getAllItems() throws SQLException {
        return itemDao.getAllItems();
    }

    public Item getItemById(int id) throws SQLException {
        return itemDao.getItemById(id);
    }

    public boolean updateItem(Item item) throws SQLException {
        return itemDao.updateItem(item);
    }

    public boolean deleteItem(int id) throws SQLException {
        return itemDao.deleteItem(id);
    }
}

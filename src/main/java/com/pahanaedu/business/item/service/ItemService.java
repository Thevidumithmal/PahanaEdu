package com.pahanaedu.business.item.service;

import com.pahanaedu.persistence.itemdao.ItemDao;
import com.pahanaedu.business.item.dto.ItemDTO;
import com.pahanaedu.business.item.mapper.ItemMapper;
import com.pahanaedu.business.item.model.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ItemService {
    private final ItemDao itemDao;

    public ItemService(Connection connection) {
        this.itemDao = new ItemDao(connection);
    }

    public boolean addItem(ItemDTO itemDTO) throws SQLException {
        Item item = ItemMapper.toModel(itemDTO);
        return itemDao.addItem(item);
    }

    public List<ItemDTO> getAllItems() throws SQLException {
        List<Item> items = itemDao.getAllItems();
        return items.stream()
                .map(ItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ItemDTO> getItemsByCategory(int categoryId) throws SQLException {
        List<Item> items = itemDao.getItemsByCategory(categoryId);
        return items.stream()
                .map(ItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ItemDTO getItemById(int id) throws SQLException {
        Item item = itemDao.getItemById(id);
        return ItemMapper.toDTO(item);
    }

    public boolean updateItem(ItemDTO itemDTO) throws SQLException {
        Item item = ItemMapper.toModel(itemDTO);
        return itemDao.updateItem(item);
    }

    public boolean deleteItem(int id) throws SQLException {
        return itemDao.deleteItem(id);
    }

    public boolean isNameExists(String name) throws SQLException {
        return itemDao.isNameExists(name);
    }

}

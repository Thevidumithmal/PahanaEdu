package com.pahanaedu.mapper;

import com.pahanaedu.dto.ItemDTO;
import com.pahanaedu.model.Item;

public class ItemMapper {

    public static ItemDTO toDTO(Item item) {
        if (item == null) return null;
        return new ItemDTO(item.getId(), item.getName(), item.getPrice());
    }

    public static Item toModel(ItemDTO dto) {
        if (dto == null) return null;
        Item item = new Item();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setPrice(dto.getPrice());
        return item;
    }
}

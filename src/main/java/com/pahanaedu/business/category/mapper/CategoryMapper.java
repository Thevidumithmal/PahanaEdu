package com.pahanaedu.business.category.mapper;


import com.pahanaedu.business.category.dto.CategoryDTO;
import com.pahanaedu.business.category.model.Category;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        if (category == null) return null;
        return new CategoryDTO(category.getId(), category.getName());
    }

    public static Category toModel(CategoryDTO dto) {
        if (dto == null) return null;
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }
}


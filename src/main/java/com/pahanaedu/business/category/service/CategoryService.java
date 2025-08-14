package com.pahanaedu.business.category.service;


import com.pahanaedu.business.category.dto.CategoryDTO;
import com.pahanaedu.business.category.mapper.CategoryMapper;
import com.pahanaedu.business.category.model.Category;
import com.pahanaedu.persistence.categorydao.CategoryDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    private final CategoryDao categoryDao;

    public CategoryService(Connection connection) {
        this.categoryDao = new CategoryDao(connection);
    }

    public boolean addCategory(CategoryDTO dto) throws SQLException {
        Category category = CategoryMapper.toModel(dto);
        return categoryDao.addCategory(category);
    }

    public List<CategoryDTO> getAllCategories() throws SQLException {
        List<Category> categories = categoryDao.getAllCategories();
        return categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    public boolean deleteCategory(int id) throws SQLException {
        return categoryDao.deleteCategory(id);
    }
}


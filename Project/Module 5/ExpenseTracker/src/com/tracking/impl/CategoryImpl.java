package com.tracking.impl;

import com.tracking.intf.CategoryIntf;
import com.tracking.models.Category;
import com.tracking.repository.CategoryRepository;
import java.util.Map;

public class CategoryImpl implements CategoryIntf {
    private CategoryRepository categoryRepository;

    public CategoryImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(Category category) { 
        categoryRepository.addCategory(category); 
    }

    @Override
    public Category getCategory(int categoryID) { 
        return categoryRepository.getCategory(categoryID); 
    }

    @Override
    public void updateCategory(Category category) { 
        categoryRepository.updateCategory(category); 
    }

    @Override
    public void deleteCategory(int categoryID) { 
        categoryRepository.deleteCategory(categoryID); 
    }

    @Override
    public Map<Integer, Category> getAllCategories() { 
        return categoryRepository.getAllCategories(); 
    }
}

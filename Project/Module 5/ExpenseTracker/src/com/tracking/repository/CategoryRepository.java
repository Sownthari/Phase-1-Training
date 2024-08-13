package com.tracking.repository;

import com.tracking.models.Category;
import java.util.HashMap;
import java.util.Map;

public class CategoryRepository {
    private Map<Integer, Category> categoryMap = new HashMap<>();
    private static int categoryIDCounter = 1;

    public CategoryRepository() {
        // Initialize with a default category for each user
        for (int userID = 1; userID <= 10; userID++) { 
            Category defaultCategory = new Category();
            defaultCategory.setCategoryID(categoryIDCounter);
            defaultCategory.setUserID(userID);
            defaultCategory.setCategoryName("Groceries");
            defaultCategory.setDescription("Spending on food and everyday essentials");
            addCategory(defaultCategory);
        }
    }

    // CRUD Operations
    public void addCategory(Category category) {
    	category.setCategoryID(categoryIDCounter++);
        categoryMap.put(category.getCategoryID(), category); 
    }

    public Category getCategory(int categoryID) { 
        return categoryMap.get(categoryID); 
    }

    public void updateCategory(Category category) { 
        categoryMap.put(category.getCategoryID(), category); 
    }

    public void deleteCategory(int categoryID) { 
        categoryMap.remove(categoryID); 
    }

    public Map<Integer, Category> getAllCategories() {
        return categoryMap;
    }
    
    
}

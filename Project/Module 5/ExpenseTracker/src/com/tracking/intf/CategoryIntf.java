package com.tracking.intf;

import com.tracking.models.Category;
import java.util.Map;

public interface CategoryIntf {
    void addCategory(Category category);
    Category getCategory(int categoryID);
    void updateCategory(Category category);
    void deleteCategory(int categoryID);
    Map<Integer, Category> getAllCategories();
}


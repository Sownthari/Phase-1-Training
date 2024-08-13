package com.tracking.service;

import com.tracking.impl.CategoryImpl;
import com.tracking.models.Category;

import java.util.Scanner;

public class CategoryService {
    private CategoryImpl categoryImpl;

    public CategoryService(CategoryImpl categoryImpl) {
        this.categoryImpl = categoryImpl;
    }


    public void addCategory(Scanner scanner, int loggedInUserID) {
        Category category = new Category();
        category.setUserID(loggedInUserID);

        System.out.print("Enter category name: ");
        category.setCategoryName(scanner.nextLine());

        System.out.print("Enter category description: ");
        category.setDescription(scanner.nextLine());

        categoryImpl.addCategory(category);
        System.out.println("Category added successfully.");
    }

    public void updateCategory(Scanner scanner, int loggedInUserID) {
        System.out.print("Enter category ID to update: ");
        int categoryID = scanner.nextInt();
        scanner.nextLine(); 

        Category category = categoryImpl.getCategory(categoryID);
        if (category != null && category.getUserID() == loggedInUserID) {
            System.out.print("Enter new category name (leave blank to keep current): ");
            String nameInput = scanner.nextLine();
            if (!nameInput.isEmpty()) {
                category.setCategoryName(nameInput);
            }

            System.out.print("Enter new description (leave blank to keep current): ");
            String description = scanner.nextLine();
            if (!description.isEmpty()) {
                category.setDescription(description);
            }

            categoryImpl.updateCategory(category);
            System.out.println("Category updated successfully.");
        } else {
            System.out.println("Category not found or you don't have permission to update it.");
        }
    }

    public void deleteCategory(Scanner scanner, int loggedInUserID) {
        System.out.print("Enter category ID to delete: ");
        int categoryID = scanner.nextInt();
        scanner.nextLine(); 

        Category category = categoryImpl.getCategory(categoryID);
        if (category != null && category.getUserID() == loggedInUserID) {
            categoryImpl.deleteCategory(categoryID);
            System.out.println("Category deleted successfully.");
        } else {
            System.out.println("Category not found or you don't have permission to delete it.");
        }
    }

    public void viewCategories(int loggedInUserID) {
        System.out.println("Your Categories:");
        categoryImpl.getAllCategories().values().stream()
            .filter(c -> c.getUserID() == loggedInUserID)
            .forEach(System.out::println);
        
    }
}


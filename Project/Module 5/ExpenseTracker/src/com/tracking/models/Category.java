package com.tracking.models;

public class Category {
    private int categoryID;
    private int userID;
    private String categoryName;
    private String description;

    // Getters and Setters
    public int getCategoryID() { return categoryID; }
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    @Override
    public String toString() {
        return "Category{" +
               "id=" + categoryID +
               ", name='" + categoryName + '\'' +
               ", description='" + description + '\'' +
               ", userID=" + userID +
               '}';
    }
}


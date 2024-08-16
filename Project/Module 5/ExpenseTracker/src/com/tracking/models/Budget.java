package com.tracking.models;

public class Budget {
    private int budgetID;
    private int userID;
    private int categoryID;
    private double amount;
    private String description;
    private double expense;

    // Getters and Setters
    public int getBudgetID() { return budgetID; }
    public void setBudgetID(int budgetID) { this.budgetID = budgetID; }
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }
    public int getCategoryID() { return categoryID; }
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getExpense() { return expense; }
    public void setExpense(double expense) { this.expense = expense; }
    
    @Override
    public String toString() {
        return "Budget{" +
               "id=" + budgetID +
               ", userID=" + userID +
               ", categoryID='" + categoryID + '\'' +
               ", amount=" + amount +
               ", description='" + description + '\'' +
               ", expense=" + expense +
               '}';
    }
}


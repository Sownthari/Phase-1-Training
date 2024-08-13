package com.tracking.models;

import java.util.Date;

public class Transaction {
    private int transactionID;
    private int userID;
    private int categoryID;
    private double amount;
    private Date transactionDate;
    private String description;
    private String type;

    // Getters and Setters
    public int getTransactionID() { return transactionID; }
    public void setTransactionID(int transactionID) { this.transactionID = transactionID; }
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }
    public int getCategoryID() { return categoryID; }
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public Date getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Date transactionDate) { this.transactionDate = transactionDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    @Override
    public String toString() {
        return "Transaction{" +
               "id=" + transactionID +
               ", userID=" + userID +
               ", categoryID=" + categoryID +
               ", amount=" + amount +
               ", transactionDate=" + transactionDate +
               ", description='" + description + '\'' +
               ", type=" + type +
               '}';
    }
}

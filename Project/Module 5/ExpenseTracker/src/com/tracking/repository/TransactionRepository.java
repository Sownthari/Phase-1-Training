package com.tracking.repository;

import com.tracking.models.Transaction;

import java.time.LocalDate;
import java.util.*;
import java.time.ZoneId;
import java.util.Date;

public class TransactionRepository {
    private Map<Integer, Transaction> transactionMap = new HashMap<>();
    private static int transactionIDCounter = 1;

    public void addTransaction(Transaction transaction) {
        transaction.setTransactionID(transactionIDCounter++);
        transactionMap.put(transaction.getTransactionID(), transaction);
    }

    public Transaction getTransaction(int transactionID) {
        return transactionMap.get(transactionID);
    }

    public void updateTransaction(Transaction transaction) {
        transactionMap.put(transaction.getTransactionID(), transaction);
    }

    public void deleteTransaction(int transactionID) {
        transactionMap.remove(transactionID);
    }

    public List<Transaction> getTransactionsByDate(LocalDate date) {
        List<Transaction> transactions = new ArrayList<>();
        
        for (Transaction transaction : transactionMap.values()) {
        	Date date_date = transaction.getTransactionDate();
            LocalDate transactionDate = date_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (transactionDate.equals(date)) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    public List<Transaction> getTransactionsByMonth(LocalDate date) {
        List<Transaction> transactions = new ArrayList<>();
        for (Transaction transaction : transactionMap.values()) {
            Date date_date = transaction.getTransactionDate();
            LocalDate transactionDate = date_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            if (transactionDate.getYear() == date.getYear() && transactionDate.getMonth() == date.getMonth()) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }
    
    public List<Transaction> getTransactionsByCategoryAndMonth(int categoryID) {
        List<Transaction> transactions = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        for (Transaction transaction : transactionMap.values()) {
            Date date_date = transaction.getTransactionDate();
            int transactionCategoryID = transaction.getCategoryID();
            LocalDate transactionDate = date_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            if (transactionDate.getYear() == currentDate.getYear() && 
                    transactionDate.getMonth() == currentDate.getMonth() && 
                    transactionCategoryID == categoryID &&
                    "Expense".equalsIgnoreCase(transaction.getType())) {
                        
                    transactions.add(transaction);
                }
        }
        return transactions;
    }
    
    
    
    
}

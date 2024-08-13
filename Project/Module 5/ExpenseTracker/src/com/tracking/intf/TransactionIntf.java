package com.tracking.intf;

import com.tracking.models.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionIntf {
    void addTransaction(Transaction transaction);
    Transaction getTransaction(int transactionID);
    void updateTransaction(Transaction transaction);
    void deleteTransaction(int transactionID);
    List<Transaction> getTransactionsByDate(LocalDate date);
    List<Transaction> getTransactionsByMonth(LocalDate date);
    List<Transaction> getTransactionsByCategoryAndMonth(int categoryID);
    
}

package com.tracking.impl;

import com.tracking.intf.TransactionIntf;
import com.tracking.models.Transaction;
import com.tracking.repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

public class TransactionImpl implements TransactionIntf {
    private TransactionRepository transactionRepository;

    public TransactionImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepository.addTransaction(transaction);
    }

    @Override
    public Transaction getTransaction(int transactionID) {
        return transactionRepository.getTransaction(transactionID);
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        transactionRepository.updateTransaction(transaction);
    }

    @Override
    public void deleteTransaction(int transactionID) {
        transactionRepository.deleteTransaction(transactionID);
    }

    @Override
    public List<Transaction> getTransactionsByDate(LocalDate date) {
        return transactionRepository.getTransactionsByDate(date);
    }

    @Override
    public List<Transaction> getTransactionsByMonth(LocalDate date) {
        return transactionRepository.getTransactionsByMonth(date);
    }
    
    @Override
    public List<Transaction> getTransactionsByCategoryAndMonth(int categoryID){
    	return transactionRepository.getTransactionsByCategoryAndMonth(categoryID);
    }
}

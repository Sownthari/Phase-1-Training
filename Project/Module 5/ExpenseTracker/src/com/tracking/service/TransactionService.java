package com.tracking.service;

import com.tracking.intf.BudgetIntf;
import com.tracking.intf.CategoryIntf;
import com.tracking.intf.TransactionIntf;

import com.tracking.models.Budget;
import com.tracking.models.Transaction;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {
    private TransactionIntf transactionIntf;
    private CategoryIntf categoryIntf;
    private BudgetIntf budgetIntf;

    public TransactionService(TransactionIntf transactionIntf, CategoryIntf categoryIntf, BudgetIntf budgetIntf) {
        this.transactionIntf = transactionIntf;
        this.categoryIntf = categoryIntf;
        this.budgetIntf = budgetIntf;
    }

    public void addTransactionWithCategory(Scanner scanner, int loggedInUserID) {
        Transaction transaction = new Transaction();
        transaction.setUserID(loggedInUserID);

        System.out.println("Select a category:");
        categoryIntf.getAllCategories().values().stream()
            .filter(c -> c.getUserID() == loggedInUserID)
            .forEach(c -> 
                System.out.println(c.getCategoryID() + ": " + c.getCategoryName()));

        System.out.print("Enter category ID: ");
        transaction.setCategoryID(scanner.nextInt());
        scanner.nextLine(); 

        System.out.print("Enter amount: ");
        transaction.setAmount(scanner.nextDouble());
        scanner.nextLine(); 

        System.out.print("Enter description: ");
        transaction.setDescription(scanner.nextLine());

        System.out.print("Enter type (Income/Expense): ");
        transaction.setType(scanner.nextLine());

        System.out.print("Enter date (YYYY-MM-DD): ");
        LocalDate localDate = LocalDate.parse(scanner.nextLine());
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        transaction.setTransactionDate(date);

        transactionIntf.addTransaction(transaction);
        System.out.println("Transaction added successfully.");
        
        if (transaction.getType().equalsIgnoreCase("Expense")) {
            budgetIntf.updateExpense(transaction.getCategoryID(), transaction.getAmount(), loggedInUserID);
        
            List<Budget> budgets = budgetIntf.getBudgets().stream()
                .filter(b -> b.getUserID() == loggedInUserID)
                .filter(b -> b.getCategoryID() == transaction.getCategoryID())
                .collect(Collectors.toList());

            if (!budgets.isEmpty()) {
                for (Budget budget : budgets) {
                    double currentExpense = budget.getExpense();
                    double budgetAmount = budget.getAmount();
                    double expensePercentage = (currentExpense / budgetAmount) * 100;

                    if (expensePercentage >= 90) {
                        System.out.println("Alert: You have used " + expensePercentage + "% of your budget for this category.");
                    }
                }
            }
        }

    }

    public void updateTransaction(Scanner scanner, int loggedInUserID) {
        System.out.print("Enter transaction ID to update: ");
        int transactionID = scanner.nextInt();
        scanner.nextLine();

        Transaction transaction = transactionIntf.getTransaction(transactionID);
        if (transaction != null && transaction.getUserID() == loggedInUserID) {
        	double originalAmount = transaction.getAmount();
            String originalType = transaction.getType();
            System.out.print("Enter new amount (leave blank to keep current): ");
            String amountInput = scanner.nextLine();
            if (!amountInput.isEmpty()) {
                transaction.setAmount(Double.parseDouble(amountInput));
            }

            System.out.print("Enter new description (leave blank to keep current): ");
            String description = scanner.nextLine();
            if (!description.isEmpty()) {
                transaction.setDescription(description);
            }

            System.out.print("Enter new type (Income/Expense, leave blank to keep current): ");
            String type = scanner.nextLine();
            if (!type.isEmpty()) {
                transaction.setType(type);
            }

            System.out.print("Enter new date (YYYY-MM-DD, leave blank to keep current): ");
            String dateInput = scanner.nextLine();
            if (!dateInput.isEmpty()) {
            	LocalDate localDate = LocalDate.parse(scanner.nextLine());
                Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

                transaction.setTransactionDate(date);
            }

            transactionIntf.updateTransaction(transaction);
            System.out.println("Transaction updated successfully.");
            
            if ("Expense".equalsIgnoreCase(originalType) && "Expense".equalsIgnoreCase(transaction.getType())) {
                double difference = transaction.getAmount() - originalAmount;
                budgetIntf.updateExpense(transaction.getCategoryID(), difference, loggedInUserID);
            } else if ("Expense".equalsIgnoreCase(originalType)) {
                budgetIntf.updateExpense(transaction.getCategoryID(), -originalAmount, loggedInUserID);
            } else if ("Expense".equalsIgnoreCase(transaction.getType())) {
                budgetIntf.updateExpense(transaction.getCategoryID(), transaction.getAmount(), loggedInUserID);
            }
        } else {
            System.out.println("Transaction not found or you don't have permission to update it.");
        }
    }

    public void deleteTransaction(Scanner scanner, int loggedInUserID) {
        System.out.print("Enter transaction ID to delete: ");
        int transactionID = scanner.nextInt();
        scanner.nextLine();
        Transaction transaction = transactionIntf.getTransaction(transactionID);
        
        if (transaction != null && transaction.getUserID() == loggedInUserID) {
            transactionIntf.deleteTransaction(transactionID);
            System.out.println("Transaction deleted successfully.");
            
            if ("Expense".equalsIgnoreCase(transaction.getType())) {
                budgetIntf.updateExpense(transaction.getCategoryID(), -transaction.getAmount(), loggedInUserID);
            }
        } else {
            System.out.println("Transaction not found or you don't have permission to delete it.");
        }
    }

    public void viewTransactionsByDate(Scanner scanner, int loggedInUserID) {
        System.out.print("Enter date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());
        System.out.println(date);

        var transactions = transactionIntf.getTransactionsByDate(date).stream()
        	    .filter(c -> c.getUserID() == loggedInUserID)
        	    .collect(Collectors.toList());
        if (!transactions.isEmpty()) {
            System.out.println("Transactions on " + date + ":");
            transactions.forEach(System.out::println);
        } else {
            System.out.println("No transactions found on this date.");
        }
    }

    public void viewTransactionsByMonth(Scanner scanner, int loggedInUserID) {
        System.out.print("Enter month (YYYY-MM): ");
        String[] parts = scanner.nextLine().split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), 1);

        var transactions = transactionIntf.getTransactionsByMonth(date).stream()
        	    .filter(c -> c.getUserID() == loggedInUserID)
        	    .collect(Collectors.toList());
        if (!transactions.isEmpty()) {
            System.out.println("Transactions in " + date.getMonth() + ":");
            transactions.forEach(System.out::println);
        } else {
            System.out.println("No transactions found in this month.");
        }
    }
    
    public List<Transaction> getTransactionsByCategoryAndMonth(int loggedInUserID, int categoryID) {

        List<Transaction> userTransactions = transactionIntf.getTransactionsByCategoryAndMonth(categoryID);

        return userTransactions.stream()
        	.filter(c -> c.getUserID() == loggedInUserID)
           
            .collect(Collectors.toList());
    }
}

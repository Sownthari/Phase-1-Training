package com.tracking.service;

import com.tracking.intf.BudgetIntf;
import com.tracking.intf.CategoryIntf;
import com.tracking.models.Budget;
import com.tracking.models.Transaction;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class BudgetService {
    // Use interfaces for abstraction
    private BudgetIntf budgetIntf;
    private CategoryIntf categoryIntf;
    private TransactionService transactionService;

    // Constructor injection with interfaces
    public BudgetService(BudgetIntf budgetIntf, CategoryIntf categoryIntf, TransactionService transactionService) {
        this.budgetIntf = budgetIntf;
        this.categoryIntf = categoryIntf;
        this.transactionService = transactionService;
    }

    public void addBudget(Scanner scanner, int loggedInUserID) {
        Budget budget = new Budget();
        budget.setUserID(loggedInUserID);
        
        System.out.println("Select a category:");
        categoryIntf.getAllCategories().values().stream()
            .filter(c -> c.getUserID() == loggedInUserID)
            .forEach(c -> 
                System.out.println(c.getCategoryID() + ": " + c.getCategoryName()));

        System.out.print("Enter category ID: ");
        budget.setCategoryID(scanner.nextInt());
        scanner.nextLine();
        
        List<Transaction> transactions = transactionService.getTransactionsByCategoryAndMonth(loggedInUserID, budget.getCategoryID());

        double initialExpense = transactions.stream()
            .mapToDouble(Transaction::getAmount)
            .sum();

        budget.setExpense(initialExpense);

        System.out.print("Enter budget amount: ");
        budget.setAmount(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("Enter budget description: ");
        budget.setDescription(scanner.nextLine());

        budgetIntf.addBudget(budget);
        System.out.println("Budget added successfully.");
    }

    public void updateBudget(Scanner scanner, int loggedInUserID) {
        System.out.print("Enter budget ID to update: ");
        int budgetId = scanner.nextInt();
        scanner.nextLine();

        Budget budget = budgetIntf.getBudget(budgetId);
        if (budget != null && budget.getUserID() == loggedInUserID) {
            System.out.print("Enter new amount (leave blank to keep current): ");
            String amountStr = scanner.nextLine();
            if (!amountStr.isEmpty()) {
                budget.setAmount(Double.parseDouble(amountStr));
            }

            System.out.print("Enter new description (leave blank to keep current): ");
            String description = scanner.nextLine();
            if (!description.isEmpty()) {
                budget.setDescription(description);
            }

            budgetIntf.updateBudget(budget);
            System.out.println("Budget updated successfully.");
        } else {
            System.out.println("Budget not found or you don't have permission to update it.");
        }
    }

    public void deleteBudget(Scanner scanner, int loggedInUserID) {
        System.out.print("Enter budget ID to delete: ");
        int budgetId = scanner.nextInt();
        scanner.nextLine();
        Budget budget = budgetIntf.getBudget(budgetId);
        if (budget != null && budget.getUserID() == loggedInUserID) {
        	budgetIntf.deleteBudget(budgetId);
        	System.out.println("Budget deleted successfully.");
        } else {
            System.out.println("Budget not found or you don't have permission to delete it.");
        }  
        
    }

    public List<Budget> viewBudgets(int loggedInUserID) {
        List<Budget> budgets = budgetIntf.getBudgets();
        System.out.println("Budgets for User ID " + loggedInUserID + ":");
        
        budgets = budgets.stream()
            .filter(c -> c.getUserID() == loggedInUserID)
	        .collect(Collectors.toList());
        if (!budgets.isEmpty()) {
            budgets.forEach(System.out::println);
        } else {
            System.out.println("No budgets found.");
        }

        return budgets;
    }
    
    public void resetExpenses() {
        List<Budget> budgets = budgetIntf.getBudgets();
        LocalDate today = LocalDate.now();
        if (today.getDayOfMonth() == 1) {
            for (Budget budget : budgets) {
                budget.setExpense(0.0);
                budgetIntf.updateBudget(budget);
            }
        }
    }
    
    public void updateExpense(int categoryID, double amount, int loggedInUserID) {
        List<Budget> budgets = budgetIntf.getBudgets();
        for (Budget budget : budgets) {
            if (budget.getCategoryID() == categoryID && budget.getUserID() == loggedInUserID) {
                double newExpense = budget.getExpense() + amount;
                budget.setExpense(newExpense);
                budgetIntf.updateBudget(budget);
            }
        }
    }
}

package com.tracking.repository;

import com.tracking.models.Budget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetRepository {
    private Map<Integer, Budget> budgetMap = new HashMap<>();
    private static int budgetIDCounter = 1;

    // CRUD Operations
    public void addBudget(Budget budget) { 
    	budget.setBudgetID(budgetIDCounter++);
    	budgetMap.put(budget.getBudgetID(), budget); 
    }
    public Budget getBudget(int budgetID) { 
    	return budgetMap.get(budgetID); 
    }
    public void updateBudget(Budget budget) { 
    	budgetMap.put(budget.getBudgetID(), budget); 
    }
    public void deleteBudget(int budgetID) { 
    	budgetMap.remove(budgetID); 
    }
    public List<Budget> getBudgets() {
        List<Budget> budgets = new ArrayList<>();
        for (Budget budget : budgetMap.values()) {
            budgets.add(budget);
        }
        return budgets;
    }
    
    public void resetExpenses() {
        for (Budget budget : budgetMap.values()) {
            budget.setExpense(0.0);
        }
    }

   
    public void updateBudgetExpense(int categoryID, double amount, int loggedInUserID) {
        for (Budget budget : budgetMap.values()) {
            if (budget.getCategoryID() == categoryID && budget.getUserID() == loggedInUserID) {
                double newExpense = budget.getExpense() + amount;
                budget.setExpense(newExpense);
                updateBudget(budget);
            }
        }
    }
}

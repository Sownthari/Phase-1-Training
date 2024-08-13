package com.tracking.intf;

import java.util.List;

import com.tracking.models.Budget;

public interface BudgetIntf {
    void addBudget(Budget budget);
    Budget getBudget(int budgetID);
    void updateBudget(Budget budget);
    void deleteBudget(int budgetID);
    List<Budget> getBudgets();
    void resetExpenses();
    void updateExpense(int categoryID, double amount, int loggedInUserID);
}

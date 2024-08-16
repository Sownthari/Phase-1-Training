package com.tracking.impl;

import java.util.List;

import com.tracking.intf.BudgetIntf;
import com.tracking.models.Budget;
import com.tracking.repository.BudgetRepository;

public class BudgetImpl implements BudgetIntf {
    private BudgetRepository budgetRepository;

    public BudgetImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public void addBudget(Budget budget) { budgetRepository.addBudget(budget); }

    @Override
    public Budget getBudget(int budgetID) { return budgetRepository.getBudget(budgetID); }

    @Override
    public void updateBudget(Budget budget) { budgetRepository.updateBudget(budget); }

    @Override
    public void deleteBudget(int budgetID) { budgetRepository.deleteBudget(budgetID); }
    
    @Override
    public List<Budget> getBudgets() {
        return budgetRepository.getBudgets();
    }
    
    @Override
    public void resetExpenses() { budgetRepository.resetExpenses(); }

    @Override
    public void updateExpense(int categoryID, double amount, int loggedInUserID) { budgetRepository.updateBudgetExpense
    	(categoryID, amount, loggedInUserID); }

    
}

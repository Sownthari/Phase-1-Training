package com.tracking;

import com.tracking.impl.UserImpl;
import com.tracking.intf.BudgetIntf;
import com.tracking.intf.CategoryIntf;
import com.tracking.intf.TransactionIntf;
import com.tracking.intf.UserIntf;
import com.tracking.impl.CategoryImpl;
import com.tracking.impl.TransactionImpl;
import com.tracking.impl.BudgetImpl;
import com.tracking.repository.UserRepository;
import com.tracking.repository.CategoryRepository;
import com.tracking.repository.TransactionRepository;
import com.tracking.repository.BudgetRepository;
import com.tracking.models.User;
import com.tracking.service.TransactionService;
import com.tracking.service.UserService;
import com.tracking.service.CategoryService;
import com.tracking.service.BudgetService;

import java.util.Scanner;

public class ExpenseTrackerApplication {

    public static void main(String[] args) {
    	
        // Repositories
        UserRepository userRepository = new UserRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        TransactionRepository transactionRepository = new TransactionRepository();
        BudgetRepository budgetRepository = new BudgetRepository();
        
        // Instantiate the concrete implementations
        UserIntf userIntf = new UserImpl(userRepository);
        CategoryIntf categoryIntf = new CategoryImpl(categoryRepository);
        TransactionIntf transactionIntf = new TransactionImpl(transactionRepository);
        BudgetIntf budgetIntf = new BudgetImpl(budgetRepository);

        // Services
        UserService userService = new UserService(userIntf);
        CategoryService categoryService = new CategoryService(categoryIntf);
        TransactionService transactionService = new TransactionService(transactionIntf, categoryIntf, budgetIntf);
        BudgetService budgetService = new BudgetService(budgetIntf, categoryIntf, transactionService);
        budgetService.resetExpenses();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (!SessionManager.isLoggedIn()) {
                // Handle user login
                System.out.println("Login:");
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();

                User loggedInUser = userIntf.authenticate(email, password);
                if (loggedInUser != null) {
                    SessionManager.login(loggedInUser);
                    System.out.println("Login successful! Welcome " + loggedInUser.getUserName());
                } else {
                    System.out.println("Invalid credentials!");
                    continue;
                }
            }

            // Main menu
            System.out.println("\nMain Menu:");
            System.out.println("1. Manage User");
            System.out.println("2. Manage Transactions");
            System.out.println("3. Manage Categories");
            System.out.println("4. Manage Budgets");
            System.out.println("5. Logout");
            System.out.print("Choose an option (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manageUsers(scanner, userService);
                    break;
                case 2:
                    manageTransactions(scanner, transactionService, SessionManager.getCurrentUser().getUserID());
                    break;
                case 3:
                    manageCategories(scanner, categoryService, SessionManager.getCurrentUser().getUserID());
                    break;
                case 4:
                    manageBudgets(scanner, budgetService, SessionManager.getCurrentUser().getUserID());
                    break;
                case 5:
                    SessionManager.logout();
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice, please select a valid option.");
            }
        }
    }

    private static void manageUsers(Scanner scanner, UserService userService) {
        while (true) {
            System.out.println("\nUser Management:");
            System.out.println("1. View User Details");
            System.out.println("2. Update User Details");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option (1-3): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userService.viewUserDetails();
                    break;
                case 2:
                    userService.updateUserDetails(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice, please select a valid option.");
            }
        }
    }

    

    private static void manageTransactions(Scanner scanner, TransactionService transactionService, int loggedInUserID) {
        boolean transactionRunning = true;
        while (transactionRunning) {
            System.out.println("\n--- Transaction Menu ---");
            System.out.println("1. Add Transaction");
            System.out.println("2. Update Transaction");
            System.out.println("3. Delete Transaction");
            System.out.println("4. View Transactions by Date");
            System.out.println("5. View Transactions by Month");
            System.out.println("6. Return to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    transactionService.addTransactionWithCategory(scanner, loggedInUserID);
                    break;
                case 2:
                    transactionService.updateTransaction(scanner, loggedInUserID);
                    break;
                case 3:
                    transactionService.deleteTransaction(scanner, loggedInUserID);
                    break;
                case 4:
                    transactionService.viewTransactionsByDate(scanner, loggedInUserID);
                    break;
                case 5:
                    transactionService.viewTransactionsByMonth(scanner, loggedInUserID);
                    break;
                case 6:
                    transactionRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void manageCategories(Scanner scanner, CategoryService categoryService, int loggedInUserID) {
        boolean categoryRunning = true;
        while (categoryRunning) {
            System.out.println("\n--- Category Menu ---");
            System.out.println("1. View Categories");
            System.out.println("2. Add Category");
            System.out.println("3. Update Category");
            System.out.println("4. Delete Category");
            System.out.println("5. Return to Main Menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    categoryService.viewCategories(loggedInUserID);
                    break;
                case 2:
                    categoryService.addCategory(scanner, loggedInUserID);
                    break;
                case 3:
                    categoryService.updateCategory(scanner, loggedInUserID);
                    break;
                case 4:
                    categoryService.deleteCategory(scanner, loggedInUserID);
                    break;
                case 5:
                    categoryRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void manageBudgets(Scanner scanner, BudgetService budgetService, int loggedInUserID) {
        while (true) {
            System.out.println("\nBudget Management:");
            System.out.println("1. View Budgets");
            System.out.println("2. Add Budget");
            System.out.println("3. Update Budget");
            System.out.println("4. Delete Budget");
            System.out.println("5. Return to Main Menu");
            System.out.print("Choose an option (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    budgetService.viewBudgets(loggedInUserID);
                    break;
                case 2:
                	budgetService.addBudget(scanner, loggedInUserID);
                    break;
                case 3:
                    budgetService.updateBudget(scanner, loggedInUserID);
                case 4:
                	budgetService.deleteBudget(scanner, loggedInUserID);
                case 5:
                	return;
                	
                default:
                    System.out.println("Invalid choice, please select a valid option.");
            }
        }
    }

}

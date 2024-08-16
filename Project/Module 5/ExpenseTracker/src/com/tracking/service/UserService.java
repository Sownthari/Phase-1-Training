package com.tracking.service;

import java.util.Scanner;

import com.tracking.SessionManager;
import com.tracking.intf.UserIntf;
import com.tracking.models.User;


public class UserService {
	
	private UserIntf userIntf;

    public UserService(UserIntf userIntf) {
        this.userIntf = userIntf;
    }
	public void viewUserDetails() {
        User currentUser = SessionManager.getCurrentUser();
        System.out.println("User Details:");
        System.out.println("ID: " + currentUser.getUserID());
        System.out.println("Username: " + currentUser.getUserName());
        System.out.println("Email: " + currentUser.getEmail());
        System.out.println("Created At: " + currentUser.getCreated_at());
        System.out.println("Updated At: " + currentUser.getUpdated_at());
    }

    public void updateUserDetails(Scanner scanner) {
        User currentUser = SessionManager.getCurrentUser();
        System.out.println("Update User Details:");
        System.out.print("New Username: ");
        String newUsername = scanner.nextLine();
        System.out.print("New Email: ");
        String newEmail = scanner.nextLine();
        System.out.print("New Password: ");
        String newPassword = scanner.nextLine();

        currentUser.setUserName(newUsername);
        currentUser.setEmail(newEmail);
        currentUser.setPassword(newPassword);
        currentUser.setUpdated_at("2024-08-12");

        userIntf.updateUser(currentUser);
        System.out.println("User details updated successfully.");
    }

}

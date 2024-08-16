package com.tracking.repository;

import com.tracking.models.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<Integer, User> userMap = new HashMap<>();
    private static int userIDCounter = 1;

    public UserRepository() {
        // Initialize default users
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUserID(userIDCounter++);
            user.setUserName("User" + i);
            user.setEmail("user" + i + "@gmail.com");
            user.setPassword("password" + i);
            user.setCreated_at("2024-08-12");
            user.setUpdated_at("2024-08-12");
            addUser(user);
        }
    }

    // CRUD Operations
    public void addUser(User user) { userMap.put(user.getUserID(), user); }
    public User getUser(int userID) { return userMap.get(userID); }
    public void updateUser(User user) { userMap.put(user.getUserID(), user); }
    
    // Authentication
    public User authenticate(String email, String password) {
        for (User user : userMap.values()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}

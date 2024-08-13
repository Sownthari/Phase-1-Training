package com.tracking.intf;

import com.tracking.models.User;

public interface UserIntf {
    void addUser(User user);
    User getUser(int userID);
    void updateUser(User user);
    User authenticate(String email, String password);
}


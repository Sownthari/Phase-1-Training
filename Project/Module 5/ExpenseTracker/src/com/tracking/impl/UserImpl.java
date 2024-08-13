package com.tracking.impl;

import com.tracking.intf.UserIntf;
import com.tracking.models.User;
import com.tracking.repository.UserRepository;

public class UserImpl implements UserIntf {
    private UserRepository userRepository;

    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) { userRepository.addUser(user); }

    @Override
    public User getUser(int userID) { return userRepository.getUser(userID); }

    @Override
    public void updateUser(User user) { userRepository.updateUser(user); }

    @Override
    public User authenticate(String email, String password) {
    	
        return userRepository.authenticate(email, password);
    }
}

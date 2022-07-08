package com.example.demo.services;

import com.example.demo.model.Item;
import com.example.demo.model.User;
import java.util.List;


public interface UserService {
    public boolean addUser(User user);
    public boolean deleteUserById(int userId);
    public User updateUser(User user, int userId);
    public User getUserById(int userId);
    public boolean userExists(int userId);
    public List<User> getAllUsers();
    public User getUserByEmail(String userEmail);
}

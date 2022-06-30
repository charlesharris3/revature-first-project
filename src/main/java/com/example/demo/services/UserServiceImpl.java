package com.example.demo.services;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean addUser(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUserById(int itemId) {
        userRepository.deleteById(itemId);
        return true;
    }

    @Override
    public User updateUser(User user, int userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User","Id",userId));
        existingUser.setUserPassword((user.getUserPassword()));
        existingUser.setUserEmail((user.getUserEmail()));
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
    }

    @Override
    public boolean userExists(int userId) {
        return userRepository.existsById(userId);
    }

   /* public List<Item> getUserCart(int userId) {
        return userRepository.getById(userId).getUserCart();
    }*/

   /* @Override
    public boolean itemExists(String itemName) {
        return userRepository.itemExists(itemName);
    } */

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String userEmail) {
        return userRepository.getUserByEmail(userEmail);
    }
}

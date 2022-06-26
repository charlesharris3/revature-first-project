package com.example.demo.repository;

import com.example.demo.model.Item;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where itemEmail = ?1") //Note: Query is being written on model class - do not query table name where data is stored. query the model class name
    public User getUserByEmail(String userEmail);
}

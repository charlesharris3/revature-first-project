package com.example.demo.repository;


import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;


//UserRepository class extends the JpaRepository class and inherits JPA methods

@Component
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where userEmail = ?1") //Note: Query is being written on model class - do not query table name where data is stored. query the model class name
    public User getUserByEmail(String userEmail);
}

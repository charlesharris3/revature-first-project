package com.example.demo.repository;

import com.example.demo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemRepository extends JpaRepository<Item,Integer> {
    @Query("select i from Item i where itemName = ?1") //Note: Query is being written on model class - do not query table name. query the model class name
    public List<Item> getItemsByName(String itemName);

   /* @Query("select i from Item i where itemName = ?1") Note: Query is being written on model class - do not query table name. query the model class name
    public boolean itemExists(String itemName); */
}

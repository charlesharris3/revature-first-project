package com.example.demo.repository;

import com.example.demo.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemRepository extends JpaRepository<Item,Integer> {
    /* Select all items in the database where the name of the item is given by the user through the API */
    @Query("select i from Item i where itemName = ?1") //Note: Query is being written on model class - do not query table name where data is stored. query the model class name
    public List<Item> getItemsByName(String itemName);
}

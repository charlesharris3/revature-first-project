package com.example.demo.services;

import com.example.demo.model.Item;
import org.springframework.stereotype.Component;

import java.util.List;


public interface ItemService { //method names are the same as ItemController.java HTTP method names
    public boolean addItem(Item item);
    public boolean deleteItemById(Item item);
    public boolean updateItem(Item item);
    public Item getItemById(int itemId);
    public boolean itemExists(int itemId);
    public String getItemQuantity(String itemQuantity);
    public List<Item> getItemByName(String itemName);
    public List<Item> getAllItems();
}

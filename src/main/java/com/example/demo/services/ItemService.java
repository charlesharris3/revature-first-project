package com.example.demo.services;

import com.example.demo.model.Item;
import java.util.List;
import java.util.Optional;

public interface ItemService { //method names are the same as ItemController.java HTTP method names
    public boolean addItem(Item item);
    public boolean deleteItemById(int itemId);
    public boolean updateItem(Item item);
    public Item getItem(int itemId);
    public boolean itemExists(int itemId);
    public List<Item> getAllItems();
    public List<Item> getItemsByName(String itemName);

}

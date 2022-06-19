package com.example.demo.services;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemRepository itemRepository;

    @Override
    public boolean addItem(Item item) {
        itemRepository.save(item);
        return true;
    }

    @Override
    public boolean deleteItemById(Item item) {
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public Item getItemById(int itemId) {
        return null;
    }

    @Override
    public boolean itemExists(int itemId) {
        return false;
    }

    @Override
    public String getItemQuantity(String itemQuantity) {
        return null;
    }

    @Override
    public List<Item> getItemByName(String itemName) {
        return null;
    }

    @Override
    public List<Item> getAllItems() {
        return null;
    }
}

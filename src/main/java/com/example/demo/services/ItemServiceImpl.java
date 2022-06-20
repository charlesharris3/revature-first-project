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
    public boolean deleteItemById(int itemId) {
        itemRepository.deleteById(itemId);
        return true;
    }

    @Override
    public boolean updateItem(Item item) {
        itemRepository.save(item);
        return true;
    }

    @Override
    public Item getItemById(int itemId) {
        return itemRepository.getById(itemId);
    }

    @Override
    public boolean itemExists(int itemId) {
        return itemRepository.existsById(itemId);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    //////
    @Override
    public String getItemQuantity(String itemQuantity) {
        return null;
    }

    @Override
    public List<Item> getItemsByName(String itemName) {
        return null;
    }


}

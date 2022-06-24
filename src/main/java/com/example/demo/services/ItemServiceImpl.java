package com.example.demo.services;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Item updateItem(Item item, int itemId) {
        Item existingItem = itemRepository.findById(itemId).orElseThrow(
                () -> new ResourceNotFoundException("Employee","Id",itemId));
        //existingItem.setItemId(item.getItemId());
        existingItem.setTotalItemQuantity((item.getTotalItemQuantity()));
        existingItem.setItemPrice((item.getItemPrice()));
        existingItem.setItemName((item.getItemName()));
        itemRepository.save(existingItem);
        return existingItem;
    }

    @Override
    public Item getItemById(int itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new ResourceNotFoundException("Item","Id",itemId));
    }

    @Override
    public boolean itemExists(int itemId) {
        return itemRepository.existsById(itemId);
    }

    /*@Override
    public boolean itemExists(String itemName) {
        return itemRepository.itemExists(itemName);
    }*/

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    //////
    public Item getItemQuantity(int itemId){
        Item item = itemRepository.getById(itemId);
        return item;
    }

    @Override
    public List<Item> getItemsByName(String itemName) {
        return itemRepository.getItemsByName(itemName);
    }


}

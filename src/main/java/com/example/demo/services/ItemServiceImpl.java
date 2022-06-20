package com.example.demo.services;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
    public boolean updateItem(Item item) {
        itemRepository.save(item);
        return false;
    }

    @Override
    public Item getItem(int itemId) {
        return itemRepository.findById(itemId).get();
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
    public Item getItemQuantity(int itemId){
        Item item = itemRepository.getById(itemId);
        return item;
    }

    @Override
    public List<Item> getItemsByName(String itemName) {
        return null;
    }


}

package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired //Injects Item class into Spring - this is a Bean
    Item item;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/home") //http://localhost:8084/item/home
    public String home(){
        return "This is the item controller: " + item;
    }

    @GetMapping("/getAllItems") //http://localhost:8084/item/getAllItems
    public String getAllItems(){
        return "A list of items";
    }

    //Create a new product using the POST HTTP method
    @PostMapping("/addItem") //http://localhost:8084/item/addItem
    public String addItem(@RequestBody Item item){
        itemRepository.save(item);
        return "Successfully saved item "+ item;
    }

    //Return an item by its ID- using @PathVariable annotation
    @GetMapping("/getItemById/{itemId}") //http://localhost:8084/item/getItemById
    public Item getItemById(@PathVariable("itemId")int itemId){
        System.out.println("Retrieving item by id: "+ item.getItemId());
        Item item = new Item(itemId,1,10,"Toilet Paper");
        return item;
    }

    //Return an item by its name - using @PathVariable annotation
    @GetMapping("/getItemByName/{itemName}") //http://localhost:8084/item/getItemByName
    public Item getItemByName(@PathVariable("itemName")String itemName){
        System.out.println("Retrieving item: "+ item.getItemName());
        Item item = new Item(100,1,10,itemName);
        return item;
    }

    //Update a product using the PUT HTTP method
    @PutMapping("/updateItem") //http://localhost:8084/item/updateItem
    public String updateItem(@RequestBody Item item){
        System.out.println("Updating item: "+ item.getItemName());
        return "Successfully updated item "+ item;
    }

    //Delete an item by its ID - using @PathVariable: @DeleteMapping
    @DeleteMapping("/removeItemById/{itemId}") //http://localhost:8084/item/removeItemById
    public String deleteItemById(@PathVariable("itemId")int itemId){
        Item item = new Item(itemId,100,10,"Item Name");
        return "Removing item by id: "+ itemId;
    }


    //Return number of items currently in stock
    @GetMapping("/getItemQuantity/{itemName}") //http://localhost:8084/item/getItemQuantity
    public String getItemQuantity(@PathVariable("itemName")String itemName){
        int totalItemQuantity = 1000000; //1 million items
        Item item = new Item(1, totalItemQuantity,10,itemName);
        return "The total quantity of item "+ item.getItemName() +" is "+ item.getTotalItemQuantity();
    }



}

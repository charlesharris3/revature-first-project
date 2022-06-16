package com.example.demo.controller;

import com.example.demo.model.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class ItemController {

    @GetMapping() //http://localhost:8080/item/
    public String home(){
        return "This is the item controller";
    }

    //Return an item by its ID- using @PathVariable annotation
    @GetMapping("/getItemById/{itemId}") //http://localhost:8080/item/getItemById
    public Item getItemById(@PathVariable("itemId")int itemId){
        System.out.println("Retrieving item by id: "+ itemId);
        Item item = new Item(itemId,1,10,"Toilet Paper");
        return item;
    }

    //Return an item by its name - using @PathVariable annotation
    @GetMapping("/getItemByName/{itemName}") //http://localhost:8080/item/getItemByName
    public Item getItemByName(@PathVariable("itemName")String itemName){
        System.out.println("Retrieving item: "+ itemName);
        Item item = new Item(100,1,10,itemName);
        return item;
    }

    //Return number of items currently in stock
    @GetMapping("/getItemQuantity/{itemName}") //http://localhost:8080/item/getItemQuantity
    public Item getItemQuantity(@PathVariable("itemName")String itemName){
        int totalItemQuanity = 1000000; //1 million items
        System.out.println("The total number of items for: "+ itemName+" is "+ totalItemQuanity);
        Item item = new Item(100, totalItemQuanity,10,itemName);
        return item;
    }

}

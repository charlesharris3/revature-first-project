package com.example.demo.controller;

import com.example.demo.model.Item;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("item")
public class ItemController {

    @GetMapping("/home") //http://localhost:8080/item/home
    public String home(){
        return "This is the item controller";
    }

    @GetMapping("/getAllItems") //http://localhost:8080/item/getAllItems
    public String getAllItems(){
        return "A list of items";
    }

    //Create a new product using the POST HTTP method
    @PostMapping("/addItem")
    public String addItem(@RequestBody Item item){
        System.out.println("Adding new item: "+ item);
        return "Successfully saved item "+ item;
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

    //Update a product using the PUT HTTP method
    @PutMapping("/updateItem")
    public String updateItem(@RequestBody Item item){
        System.out.println("Updating item: "+ item);
        return "Successfully updated item "+ item;
    }

    //Delete an item by its ID - using @PathVariable: @DeleteMapping
    @DeleteMapping("/removeItemById/{itemId}") //http://localhost:8080/item/removeItemById
    public String deleteItemById(@PathVariable("itemId")int itemId){
        Item item = new Item(itemId,1,10,"Toilet Paper");
        return "Removing item by id: "+ itemId;
    }


    //Return number of items currently in stock
    @GetMapping("/getItemQuantity/{itemName}") //http://localhost:8080/item/getItemQuantity
    public String getItemQuantity(@PathVariable("itemName")String itemName){
        int totalItemQuantity = 1000000; //1 million items
        Item item = new Item(100, totalItemQuantity,10,itemName);
        return "The total quantity of item "+ item.getItemName() +" is "+ item.getTotalItemQuantity();
    }



}

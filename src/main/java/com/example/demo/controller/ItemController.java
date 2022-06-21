package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired //Injects Item class into Spring - this is a Bean
    Item item;
    @Autowired
    ItemService itemService;

    ResponseEntity responseEntity = null;

    @GetMapping("/home") //http://localhost:8084/item/home
    public String home(){
        return "This is the item controller: ";
    }

    @GetMapping("/getAllItems") //http://localhost:8084/item/getAllItems
    public ResponseEntity<List<Item>> getAllItems(){
        List<Item> itemsList = new ArrayList<Item>();
        itemsList = itemService.getAllItems();
        return new ResponseEntity<List<Item>>(itemsList,HttpStatus.OK);
    }

    //Create a new product using the POST HTTP method
    @PostMapping("/addItem") //http://localhost:8084/item/addItem
    public ResponseEntity<String> addItem(@RequestBody Item item){
        if(itemService.itemExists(item.getItemId())){
            return new ResponseEntity<String>("The item with ID "+item.getItemId()+" already exists.", HttpStatus.ACCEPTED);
        }
        else{
            itemService.addItem(item);
        } return new ResponseEntity<String>("The item with ID "+item.getItemId()+" has been added to the database.", HttpStatus.OK);
    }

    //Return an item by its ID- using @PathVariable annotation
    @GetMapping("/getItemById/{itemId}") //http://localhost:8084/item/getItemById
    public ResponseEntity<Item> getItem(@PathVariable("itemId")int itemId){
        Item item = new Item();
        if(itemService.itemExists(itemId)){
            item = itemService.getItem(itemId);
            return new ResponseEntity<Item>(item,HttpStatus.OK);
        }
        else
            return new ResponseEntity<Item>(item,HttpStatus.NO_CONTENT);
    }

    /* needs work!!!! */
    //Update a product using the PUT HTTP method
    @PutMapping("/updateItem") //http://localhost:8084/item/updateItem
    public String updateItem(@RequestBody Item item){
        itemService.updateItem(item);
        return "Successfully updated item "+ item;
    }

    //Delete an item by its ID - using @PathVariable: @DeleteMapping
    @DeleteMapping("/removeItemById/{itemId}") //http://localhost:8084/item/removeItemById
    public String deleteItemById(@PathVariable("itemId")int itemId){
        itemService.deleteItemById(itemId);
        return "Removing item by id: "+ itemId;
    }

    @GetMapping("/doesItemExist/{itemId}") //http:localhost:8084/item/doesItemExist
    public boolean doesItemExist(@PathVariable("itemId") int itemId){
        return itemService.itemExists(itemId);
    }

    // Return number of items currently in stock
    @GetMapping("/getItemQuantity/{itemId}") //http://localhost:8084/item/getItemQuantity
    public String getItemQuantity(@PathVariable("itemId")int itemId){
        Item item = itemService.getItem(itemId);
        return "The number of products for "+item.getItemName()+" currently in stock is "+item.getTotalItemQuantity();
    }

    /*  Return an item by its name - using @PathVariable annotation
    @GetMapping("/getItemByName/{itemName}") //http://localhost:8084/item/getItemByName
    public Item getItemByName(@PathVariable("itemName")String itemName){
        System.out.println("Retrieving item: "+ item.getItemName());
        Item item = new Item(100,1,10,itemName);
        return item;
    } */

}

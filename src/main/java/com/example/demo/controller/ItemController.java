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

    /* needs work!!!! - not updating items */
    //Update a product using the PUT HTTP method
    @PutMapping("/updateItem/{itemId}") //http://localhost:8084/item/updateItem
    public ResponseEntity<Item> updateItem(@PathVariable("itemId") int itemId, @RequestBody Item item){
        Item existingItem = itemService.getItem(itemId);
        if(itemService.itemExists(itemId)) {
            existingItem.setItemId(item.getItemId());
            existingItem.setTotalItemQuantity(item.getTotalItemQuantity());
            existingItem.setItemPrice(item.getItemPrice());
            existingItem.setItemName(item.getItemName());
            itemService.updateItem(existingItem);
            return new ResponseEntity<Item>(existingItem,HttpStatus.OK);
        }
        else
            return new ResponseEntity<Item>(existingItem,HttpStatus.NOT_FOUND);
    }

    //Delete an item by its ID - using @PathVariable: @DeleteMapping
    @DeleteMapping("/removeItemById/{itemId}") //http://localhost:8084/item/removeItemById
    public ResponseEntity<String> deleteItemById(@PathVariable("itemId")int itemId){
        if(itemService.itemExists(itemId)){
            itemService.deleteItemById(itemId);
            return new ResponseEntity<String>("Item "+itemId+" has been removed from the database.",HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("Item "+itemId+" does not exist.",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/doesItemExist/{itemId}") //http:localhost:8084/item/doesItemExist
    public ResponseEntity<String> doesItemExist(@PathVariable("itemId") int itemId){
        if(itemService.itemExists(itemId)){
            return new ResponseEntity<String>("Item "+itemId+" exists.",HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Item "+itemId+" does not exists.",HttpStatus.NOT_FOUND);
    }

    // Return number of items currently in stock
    @GetMapping("/getItemQuantity/{itemId}") //http://localhost:8084/item/getItemQuantity
    public ResponseEntity<String> getItemQuantity(@PathVariable("itemId")int itemId){
        Item item = null;
        if(itemService.itemExists(itemId)){
            item = itemService.getItem(itemId);
            return new ResponseEntity<String>("The number of products for "+item.getItemName()+" currently in stock is "+item.getTotalItemQuantity(),HttpStatus.OK);
        } else
            return new ResponseEntity<String>("Item "+itemId+" does not exists.",HttpStatus.NOT_FOUND);
    }

    /*  Return an item by its name - using @PathVariable annotation
    @GetMapping("/getItemByName/{itemName}") //http://localhost:8084/item/getItemByName
    public Item getItemByName(@PathVariable("itemName")String itemName){
        System.out.println("Retrieving item: "+ item.getItemName());
        Item item = new Item(100,1,10,itemName);
        return item;
    } */

}

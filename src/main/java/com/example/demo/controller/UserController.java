package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.model.User;
import com.example.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired //Injects User class into Spring - this is a Bean
    User user;
    @Autowired
    UserService userService;

    @Autowired
    Item itemService;


    ResponseEntity responseEntity = null; //Initialize a ResponseEntity object with value of 'null'

    //Create logs for the UserController
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping() //http://localhost:8084/user/
    public String home(){
        LOGGER.info("The user controller is being called from endpoint '/home'");
        return "This is the user controller";
    }

    @GetMapping("/getAllUsers") //http://localhost:8084/user/getAllUsers
    public ResponseEntity<List<User>> getAllUsers(){
        LOGGER.info("The user controller is being called from endpoint '/getAllUsers'");
        List<User> usersList = new ArrayList<User>();
        usersList = userService.getAllUsers();
        return new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
    }

    //Add a new user using the POST HTTP method
    @PostMapping("/registerUser") //http://localhost:8084/user/registerUser
    public ResponseEntity<String> registerUser(@RequestBody User user){
        if(userService.userExists(user.getUserId())){
            LOGGER.warn("User "+user.getUserId()+" already exists.");
            return new ResponseEntity<String>("The user with ID "+user.getUserId()+" already exists.", HttpStatus.ALREADY_REPORTED);
        }
        else{
            userService.addUser(user);
            LOGGER.info("The user with ID "+user.getUserId()+" has a new account.");
        } return new ResponseEntity<String>("The user with ID "+user.getUserId()+" has been added to the database.", HttpStatus.CREATED);
    }

    //Return an user by its ID- using @PathVariable annotation
    @GetMapping("/getUserById/{userId}") //http://localhost:8084/user/getUserById
    public ResponseEntity<User> getUserById(@PathVariable("userId")int userId){
        User user = new User();
        if(userService.userExists(userId)){
            user = userService.getUserById(userId);
            LOGGER.info("Returning user "+userId);
            return new ResponseEntity<User>(user,HttpStatus.OK);
        }
        else
            LOGGER.warn("The user "+userId+" does not exist.");
        return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
    }
    //Return all users with the same name
    @GetMapping("/getUserByEmail/{userEmail}") //http://localhost:8084/user/getUserByEmail
    public ResponseEntity<User> getUserByEmail(@PathVariable("userEmail")String userEmail){
        User user = userService.getUserByEmail(userEmail);
        if(userService.userExists(user.getUserId())) {
            LOGGER.info("Returning user: " + user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        else
            LOGGER.warn("There are no users by the name "+userEmail+" in the database");
        return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
    }

    /*
    @GetMapping("/getUserCart/{userId}") //http://localhost:8084/user/getUserCart
    public ResponseEntity<List<Item>> getUserCart(@PathVariable("userId")int userId){
        User user = userService.getUserById(userId);
        if(userService.userExists(user.getUserId())) {
            LOGGER.info("Here is the cart for "+userId+": "+ user.getUserCart());
            return new ResponseEntity<List<Item>>(user.getUserCart(), HttpStatus.OK);
        }
        else
            LOGGER.warn("There is no cart for this user");
        return new ResponseEntity<List<Item>>(user.getUserCart(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/insertItemToCart/{userId}") //http://localhost:8084/user/getUserCart
    public ResponseEntity<List<Item>> insertItemToCart(@PathVariable("userId")int userId, @PathVariable("itemId")int itemId){
        User user = userService.getUserById(userId);
        Item item = new Item();
        if(userService.userExists(user.getUserId())) {
            LOGGER.info("Here is the cart for "+userId+": "+ user.setUserCart(item.getItemId();
            return new ResponseEntity<List<Item>>(user.getUserCart(), HttpStatus.OK);
        }
        else
            LOGGER.warn("There is no cart for this user");
        return new ResponseEntity<List<Item>>(user.getUserCart(), HttpStatus.NOT_FOUND);
    }*/

    //Update a product using the PUT HTTP method
    @PutMapping("/updateUser/{userId}") //http://localhost:8084/user/updateUser
    public ResponseEntity<User> updateUser(@PathVariable("userId") int userId, @RequestBody User user){
        LOGGER.info("User "+userId+" has been updated");
        return new ResponseEntity<User>(userService.updateUser(user,userId),HttpStatus.OK);
    }

    //Delete an user by its ID - using @PathVariable: @DeleteMapping
    @DeleteMapping("/removeUserById/{userId}") //http://localhost:8084/user/removeUserById
    public ResponseEntity<String> deleteUserById(@PathVariable("userId")int userId){
        if(userService.userExists(userId)){
            userService.deleteUserById(userId);
            LOGGER.info("User "+userId+" has been removed.");
            return new ResponseEntity<String>("User "+userId+" has been removed from the database.",HttpStatus.OK);
        }
        else
            LOGGER.warn("User "+userId+" could not be found");
        return new ResponseEntity<String>("User "+userId+" does not exist.",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/doesUserExist/{userId}") //http:localhost:8084/user/doesUserExist
    public ResponseEntity<String> doesUserExist(@PathVariable("userId") int userId){
        if(userService.userExists(userId)){
            LOGGER.info("User "+userId+" exists.");
            return new ResponseEntity<String>("User "+userId+" exists.",HttpStatus.OK);
        } else
            LOGGER.warn("User "+userId+" could not be found");
        return new ResponseEntity<String>("User "+userId+" does not exists.",HttpStatus.NOT_FOUND);
    }

}

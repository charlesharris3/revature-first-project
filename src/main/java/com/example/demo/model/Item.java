package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data; //lombok library for standard class methods
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component; //creates Bean

import javax.persistence.*;

@Component //used to generate this class as a Bean
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items", schema = "revature_db")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private int totalItemQuantity;
    private int itemPrice;
    private String itemName;
}

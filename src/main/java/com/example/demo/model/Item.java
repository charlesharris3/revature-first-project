package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data; //lombok library for standard class methods
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private int itemId;
    private int totalItemQuantity;
    private int itemPrice;
    private String itemName;
}

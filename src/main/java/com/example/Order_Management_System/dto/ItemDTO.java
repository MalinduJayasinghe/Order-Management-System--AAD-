package com.example.Order_Management_System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private long itemId;
    private String itemName;
    private double price;

    public ItemDTO(String itemName, double price) {
        this.itemName = itemName;
        this.price = price;
    }
}

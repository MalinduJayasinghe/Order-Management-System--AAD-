package com.example.Order_Management_System.service;

import com.example.Order_Management_System.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    void saveItem(ItemDTO itemDTO);
    List<ItemDTO> filterItems(String itemName);
}

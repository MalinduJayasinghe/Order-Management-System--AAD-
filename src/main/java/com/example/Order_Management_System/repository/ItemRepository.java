package com.example.Order_Management_System.repository;

import com.example.Order_Management_System.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT * FROM item WHERE (?1 IS NULL OR item_name LIKE  %?1%)" ,nativeQuery = true)
    List<Item> filterItems(String itemName);
}

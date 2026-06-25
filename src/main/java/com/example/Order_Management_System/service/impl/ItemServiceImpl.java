package com.example.Order_Management_System.service.impl;

import com.example.Order_Management_System.dto.ItemDTO;
import com.example.Order_Management_System.entity.Item;
import com.example.Order_Management_System.repository.ItemRepository;
import com.example.Order_Management_System.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public void saveItem(ItemDTO itemDTO) {

        log.info("saveItem");

        try {

            Item item = new Item();
            item.setItemName(itemDTO.getItemName());
            item.setPrice(itemDTO.getPrice());

            itemRepository.save(item);

        }catch (Exception e){
            log.error("saveItem");
            throw e;
        }
    }

    @Override
    public List<ItemDTO> filterItems(String itemName) {

        log.info("filterItems");

        try {

            List<ItemDTO> responseList = new ArrayList<>();
            List<Item> itemList = itemRepository.filterItems(itemName);

            for (Item item : itemList) {

                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setItemId(item.getItemId());
                itemDTO.setItemName(item.getItemName());
                itemDTO.setPrice(item.getPrice());

                responseList.add(itemDTO);
            }

            return responseList;

        }catch (Exception e){
            log.error("filterItems");
            throw e;
        }
    }
}

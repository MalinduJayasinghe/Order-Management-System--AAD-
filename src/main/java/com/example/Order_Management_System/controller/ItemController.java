package com.example.Order_Management_System.controller;

import com.example.Order_Management_System.constant.CommonResponse;
import com.example.Order_Management_System.dto.ItemDTO;
import com.example.Order_Management_System.entity.Item;
import com.example.Order_Management_System.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.Order_Management_System.constant.ResponseCode.OPERATION_SUCCESS;
import static com.example.Order_Management_System.constant.ResponseMessage.SUCCESS_MESSAGE;

@RestController
@RequestMapping(value = "v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveItem(@RequestBody ItemDTO itemDTO){

        itemService.saveItem(itemDTO);
        return new CommonResponse(SUCCESS_MESSAGE, OPERATION_SUCCESS);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterItems(@RequestParam (value = "itemName", required = false) String itemName){

        List<ItemDTO> itemDetails =  itemService.filterItems(itemName);
        return new CommonResponse(OPERATION_SUCCESS, itemDetails, SUCCESS_MESSAGE);
    }
}

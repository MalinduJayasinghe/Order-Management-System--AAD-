package com.example.Order_Management_System.dto.response;

import com.example.Order_Management_System.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderDetailDTO {

    private long orderId;
    private LocalDate orderDate;
    private double total;

    private long userId;
    private String customerName;
    List<ItemDTO> itemList;
}

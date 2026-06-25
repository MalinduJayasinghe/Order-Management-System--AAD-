package com.example.Order_Management_System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long orderIid;
    private double total;
    private LocalDate orderDate;
    private long userId;

    private List<Long> itemIdList;
}

package com.example.Order_Management_System.service;

import com.example.Order_Management_System.dto.OrderDTO;
import com.example.Order_Management_System.dto.response.GetOrderDetailDTO;

import java.util.List;

public interface OrderService {

    void placeOrder(OrderDTO orderDTO);
    List<GetOrderDetailDTO> filterOrders(String customerName);
    List<GetOrderDetailDTO> getCustomerOrders(long userId);
}

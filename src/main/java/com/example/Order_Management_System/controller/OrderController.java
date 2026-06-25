package com.example.Order_Management_System.controller;

import com.example.Order_Management_System.constant.CommonResponse;
import com.example.Order_Management_System.dto.OrderDTO;
import com.example.Order_Management_System.dto.response.GetOrderDetailDTO;
import com.example.Order_Management_System.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.Order_Management_System.constant.ResponseCode.OPERATION_SUCCESS;
import static com.example.Order_Management_System.constant.ResponseMessage.SUCCESS_MESSAGE;

@RestController
@RequestMapping(value = "v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse placeOrder(@RequestBody OrderDTO orderDTO) {

        orderService.placeOrder(orderDTO);
        return new CommonResponse(SUCCESS_MESSAGE, OPERATION_SUCCESS);
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse filterOrders(
            @RequestParam(value = "customerName", required = false) String customerName
    ) {
        List<GetOrderDetailDTO> orderDetailDTOList = orderService.filterOrders(customerName);
        return new CommonResponse(OPERATION_SUCCESS, orderDetailDTOList, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getCustomerOrders(@PathVariable long userId) {

        List<GetOrderDetailDTO> customerOrders = orderService.getCustomerOrders(userId);
        return new CommonResponse(OPERATION_SUCCESS, customerOrders,  SUCCESS_MESSAGE);
    }
}

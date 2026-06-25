package com.example.Order_Management_System.service.impl;

import com.example.Order_Management_System.dto.ItemDTO;
import com.example.Order_Management_System.dto.OrderDTO;
import com.example.Order_Management_System.dto.response.GetOrderDetailDTO;
import com.example.Order_Management_System.entity.Item;
import com.example.Order_Management_System.entity.Order;
import com.example.Order_Management_System.entity.OrderItem;
import com.example.Order_Management_System.entity.User;
import com.example.Order_Management_System.enumeration.Role;
import com.example.Order_Management_System.repository.ItemRepository;
import com.example.Order_Management_System.repository.OrderItemRepository;
import com.example.Order_Management_System.repository.OrderRepository;
import com.example.Order_Management_System.repository.UserRepository;
import com.example.Order_Management_System.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;

    @Override
    public void placeOrder(OrderDTO orderDTO) {

        Order order = new Order();
        order.setTotal(orderDTO.getTotal());
        order.setOrderDate(orderDTO.getOrderDate());

        Optional<User> optionalUser = userRepository.findById(orderDTO.getUserId());
        if (optionalUser.isEmpty()) {

            throw new RuntimeException("Customer not found");
        }

        User user = optionalUser.get();
        if(!user.getRole().equals(Role.CUSTOMER)){
            throw new RuntimeException("User is not a customer");
        }

        order.setUser(user);
        Order savedOrder = orderRepository.save(order);

        for (Long itemId : orderDTO.getItemIdList()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);

            Optional<Item> optionalItem = itemRepository.findById(itemId);
            if (optionalItem.isEmpty()) {
                throw new RuntimeException("Item not found");
            }

            Item item = optionalItem.get();
            orderItem.setItem(item);
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public List<GetOrderDetailDTO> filterOrders(String customerName) {

        List<GetOrderDetailDTO> responseList = new ArrayList<>();
        List<Order> orderList = orderRepository.filterOrders(customerName);

        for (Order order : orderList) {

            GetOrderDetailDTO dto = new GetOrderDetailDTO();
            dto.setOrderId(order.getOrder_id());
            dto.setOrderDate(order.getOrderDate());
            dto.setTotal(order.getTotal());

            dto.setUserId(order.getUser().getUserId());
            dto.setCustomerName(order.getUser().getName());

            List<OrderItem> orderItemList = order.getOrderItemList();
            List<ItemDTO>  itemList = new ArrayList<>();

            for (OrderItem orderItem : orderItemList) {

                Item item = orderItem.getItem();

                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setItemId(item.getItemId());
                itemDTO.setItemName(item.getItemName());
                itemDTO.setPrice(item.getPrice());

                itemList.add(itemDTO);
            }

            dto.setItemList(itemList);
            responseList.add(dto);
        }

        return responseList;
    }

    @Override
    public List<GetOrderDetailDTO> getCustomerOrders(long userId) {

        List<GetOrderDetailDTO> responseList = new ArrayList<>();
        List<Order> orderList = orderRepository.getCustomerOrders(userId);

        for (Order order : orderList) {

            GetOrderDetailDTO dto = new GetOrderDetailDTO();
            dto.setOrderId(order.getOrder_id());
            dto.setOrderDate(order.getOrderDate());
            dto.setTotal(order.getTotal());

            dto.setUserId(order.getUser().getUserId());
            dto.setCustomerName(order.getUser().getName());

            List<OrderItem> orderItemList = order.getOrderItemList();
            List<ItemDTO>  itemList = new ArrayList<>();

            for (OrderItem orderItem : orderItemList) {

                Item item = orderItem.getItem();

                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setItemId(item.getItemId());
                itemDTO.setItemName(item.getItemName());
                itemDTO.setPrice(item.getPrice());

                itemList.add(itemDTO);
            }

            dto.setItemList(itemList);
            responseList.add(dto);
        }

        return responseList;
    }
}

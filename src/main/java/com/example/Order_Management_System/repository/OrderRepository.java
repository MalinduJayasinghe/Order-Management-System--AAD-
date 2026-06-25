package com.example.Order_Management_System.repository;

import com.example.Order_Management_System.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "SELECT * FROM orders o " +
            "JOIN user u ON o.user_user_id = user_id " +
            "WHERE (?1 IS NULL OR u.name LIKE %?1%) " +
            "AND (u.role = 'CUSTOMER')",
            nativeQuery = true)
    List<Order> filterOrders(String customerName);

    @Query(value = "SELECT * FROM orders o " +
            "JOIN user u ON o.user_user_id = user_id " +
            "WHERE (?1 IS NULL OR u.name LIKE %?1%) " +
            "AND (u.role = 'CUSTOMER')",
            nativeQuery = true)
    List<Order> getCustomerOrders(Long userId);
}

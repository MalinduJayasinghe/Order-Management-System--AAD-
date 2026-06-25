package com.example.Order_Management_System.repository;

import com.example.Order_Management_System.entity.Order;
import com.example.Order_Management_System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM user WHERE user_role ='CUSTOMER'", nativeQuery = true)
    List<User> getAllCustomers();

    @Query(value = "SELECT * FROM user WHERE user_role <> 'CUSTOMER'", nativeQuery = true)
    List<User> getAllEmployees();
}

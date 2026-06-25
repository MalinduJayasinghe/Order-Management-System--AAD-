package com.example.Order_Management_System.service;

import com.example.Order_Management_System.dto.UserDTO;
import com.example.Order_Management_System.enumeration.Role;

import java.util.List;

public interface UserService {

        void saveUser(UserDTO userDTO);
        List<UserDTO> getUserDetails(Role role);
        void updateUser(UserDTO userDTO);
}

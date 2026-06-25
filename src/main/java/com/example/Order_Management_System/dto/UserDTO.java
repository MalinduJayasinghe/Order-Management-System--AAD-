package com.example.Order_Management_System.dto;

import com.example.Order_Management_System.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private long userId;
    private String name;
    private String contact;
    private Role role;
}

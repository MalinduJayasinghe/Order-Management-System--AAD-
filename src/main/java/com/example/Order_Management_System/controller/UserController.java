package com.example.Order_Management_System.controller;

import com.example.Order_Management_System.constant.CommonResponse;
import com.example.Order_Management_System.dto.UserDTO;
import com.example.Order_Management_System.enumeration.Role;
import com.example.Order_Management_System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.Order_Management_System.constant.ResponseCode.OPERATION_SUCCESS;
import static com.example.Order_Management_System.constant.ResponseMessage.SUCCESS_MESSAGE;

@RestController
@RequestMapping(value = "v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveUser(@RequestBody UserDTO userDTO){

        userService.saveUser(userDTO);
        return new CommonResponse(SUCCESS_MESSAGE, OPERATION_SUCCESS);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse saveUserDetails(@RequestParam(value = "Role", required = false) Role role){

        List<UserDTO> userDetails = userService.getUserDetails(role);
        return new CommonResponse(OPERATION_SUCCESS, userDetails, SUCCESS_MESSAGE);
    };

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateUser(@RequestBody UserDTO userDTO){

        userService.updateUser(userDTO);
        return new CommonResponse(SUCCESS_MESSAGE, OPERATION_SUCCESS);
    }
}

package com.example.Order_Management_System.service.impl;

import com.example.Order_Management_System.dto.UserDTO;
import com.example.Order_Management_System.entity.User;
import com.example.Order_Management_System.enumeration.Role;
import com.example.Order_Management_System.repository.UserRepository;
import com.example.Order_Management_System.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.Order_Management_System.enumeration.Role.CUSTOMER;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public void saveUser(UserDTO userDTO) {

        log.info("Execute Method saveUser");

        try {

            User user = new User();
            user.setName(userDTO.getName());
            user.setContact(userDTO.getContact());
            user.setRole(userDTO.getRole());

            userRepository.save(user);

        }catch (Exception ex){
            log.info("Error in method saveUser:" +ex.getMessage());
        }
    }

    @Override
    public List<UserDTO> getUserDetails(Role role) {

        try {

            log.info("Execute Method getUserDetails");

            List<UserDTO> responseList = new ArrayList<>();
            List<User> allUsers = new ArrayList<>();

            if(role != null && role.equals(CUSTOMER)){

                allUsers = userRepository.getAllCustomers();
            }else {
                allUsers = userRepository.getAllEmployees();
            }

            for (User user : allUsers) {

                UserDTO userDTO = new UserDTO();
                userDTO.setUserId(user.getUserId());
                userDTO.setName(user.getName());
                userDTO.setContact(user.getContact());
                userDTO.setRole(user.getRole());

                responseList.add(userDTO);
            }

            return responseList;

        }catch (Exception ex){
            log.info("Error in method getUserDetails:" +ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {

        log.info("Execute Method updateUser");

        try {

            Optional<User> optionalUser = userRepository.findById(userDTO.getUserId());
            if (optionalUser.isEmpty()) {
                throw new RuntimeException("User not found");
            }

            User user = optionalUser.get();
            user.setName(userDTO.getName());
            user.setContact(userDTO.getContact());
            user.setRole(userDTO.getRole());

            userRepository.save(user);


        }catch (Exception ex){
            log.info("Error in method updateUser:" +ex.getMessage());
        }
    }
}

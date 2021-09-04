package com.example.demo.service;

import com.example.demo.model.dto.UserDTO;

import java.util.Set;

public interface UserService {

    Set<UserDTO> retrieveAllUsers();

    Set<UserDTO> retrieveAllAdmins();

    Set<UserDTO> retrieveAllRegularUsers();

    Set<UserDTO> retrieveUserByName(String name);

    Set<UserDTO> retrieveUserByEmail(String email);

    void deleteUserById(Integer id);

    UserDTO createUser(UserDTO userDTO);

}

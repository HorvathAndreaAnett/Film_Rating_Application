package com.example.demo.controller;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/administrator/retrieveAllUsers")
    public ResponseEntity<?> retrieveAllUsers() {
        Set<UserDTO> userDTOSet =userService.retrieveAllUsers();
        if (userDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTOSet, HttpStatus.OK);
    }


    @GetMapping("/administrator/retrieveAllRegularUsers")
    public ResponseEntity<?> retrieveAllRegularUsers() {
        Set<UserDTO> userDTOSet =userService.retrieveAllRegularUsers();
        if (userDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTOSet, HttpStatus.OK);
    }


    @GetMapping("/administrator/retrieveAllAdministrators")
    public ResponseEntity<?> retrieveAllAdministrators() {
        Set<UserDTO> userDTOSet =userService.retrieveAllAdmins();
        if (userDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTOSet, HttpStatus.OK);
    }


    @GetMapping("/administrator/retrieveUserByEmail")
    public ResponseEntity<?> retrieveUserByEmail(@RequestBody String email) {
        Set<UserDTO> userDTOSet =userService.retrieveUserByEmail(email);
        if (userDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTOSet, HttpStatus.OK);
    }


    @GetMapping("/administrator/retrieveUserByName")
    public ResponseEntity<?> retrieveUserByName(@RequestBody String name) {
        Set<UserDTO> userDTOSet =userService.retrieveUserByName(name);
        if (userDTOSet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userDTOSet, HttpStatus.OK);
    }


    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        if (createdUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }


    @DeleteMapping("/administrator/deleteUserById")
    public ResponseEntity<?> deleteUserById(@RequestBody Integer userId) {
        try {
            userService.deleteUserById(userId);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }
}

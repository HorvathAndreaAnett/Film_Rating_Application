package com.example.demo.serviceTests;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Test retrieveUserByEmail Success")
    public void retrieveUserByEmail_UserFound() {
        UserDTO expectedUserDTO = new UserDTO(1, 1, "Andrea", "Horvath", "andi@gmail.com", "andi", null);
        Set<UserDTO> returnedUserDTOSet = userService.retrieveUserByEmail("andi@gmail.com");
        Assertions.assertTrue(returnedUserDTOSet.contains(expectedUserDTO));
    }

    @Test
    @DisplayName("Test retrieveUserByEmail Failure")
    public void retrieveUserByEmail_UserNotFound() {
        Set<UserDTO> returnedUserDTOSet = userService.retrieveUserByEmail("andrea@gmail.com");
        Assertions.assertTrue(returnedUserDTOSet.isEmpty());
    }

    @Test
    @DisplayName("Test retrieveUserByName Success")
    public void retrieveUserByName_UserFound() {
        UserDTO expectedUserDTO = new UserDTO(1, 1, "Andrea", "Horvath", "andi@gmail.com", "andi", null);
        Set<UserDTO> returnedUserDTOSet = userService.retrieveUserByName("Andrea");
        Assertions.assertTrue(returnedUserDTOSet.contains(expectedUserDTO));
    }

    @Test
    @DisplayName("Test retrieveUserByName Failure")
    public void retrieveUserByName_UserNotFound() {
        Set<UserDTO> returnedUserDTOSet = userService.retrieveUserByName("Maria");
        Assertions.assertTrue(returnedUserDTOSet.isEmpty());
    }

}

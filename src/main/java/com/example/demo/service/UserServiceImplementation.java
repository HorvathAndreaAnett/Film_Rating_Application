package com.example.demo.service;

import com.example.demo.model.Constants;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Set<UserDTO> retrieveAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserDTO> retrieveAllAdmins() {
        return userRepository.findAllByRoleId(Constants.administratorRoleId)
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserDTO> retrieveAllRegularUsers() {
        return userRepository.findAllByRoleId(Constants.userRoleId)
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserDTO> retrieveUserByName(String name) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getFullName().contains(name))
                .map(UserDTO::fromEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<UserDTO> retrieveUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toSet());
    }


    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (!isUserDataValid(userDTO)) {
            return null;
        }

        User user = modelMapper.map(userDTO, User.class);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        user.setRole(roleRepository.findById(Constants.userRoleId).orElse(null));
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user = userRepository.save(user);
        return UserDTO.fromEntity(user);
    }


    private boolean isUserDataValid(UserDTO userDTO) {
        if (userDTO.getEmail().contains("@")
                && userDTO.getPassword().length() > Constants.minPasswordLength
                && userDTO.getFirstName().matches("[a-zA-Z]+")
                && userDTO.getLastName().matches("[a-zA-Z]+")
                && userDTO.getUsername().matches("[a-zA-Z1-9]+")) {
            return true;
        }
        return false;
    }


}

package com.skcet.vehicle_rental_management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skcet.vehicle_rental_management.dto.request.UserRequestDTO;
import com.skcet.vehicle_rental_management.dto.response.UserResponseDTO;
import com.skcet.vehicle_rental_management.model.User;
import com.skcet.vehicle_rental_management.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("username already exists");
        }
        User user = User.builder()
            .username(request.getUsername())
            .password(request.getPassword())
            .phone(request.getPhone()).build();
        User savedUser = userRepository.save(user);
        return UserResponseDTO.builder()
            .id(user.getUserId())
            .username(savedUser.getUsername())
            .phone(savedUser.getPhone()).build();
    }

    @Override
    public List<UserResponseDTO> getAllUser() {
        List<User> users =  userRepository.findAll();
        List<UserResponseDTO> response = new ArrayList<>();
        for(User user : users){
            UserResponseDTO dto = UserResponseDTO.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .phone(user.getPhone())
                .build();
            response.add(dto);
        }
        return response;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository
            .findById(id)
            .orElseThrow(()-> new RuntimeException("user not found"));
        return UserResponseDTO.builder()
            .id(user.getUserId())
            .username(user.getUsername())
            .phone(user.getPhone())
            .build();
    }

    @Override
    public User updateUser(Long id, User request) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());

        return userRepository.save(user);
    }

    @Override
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
        userRepository.delete(user);
        return "user deleted successfully";
    }
    
}

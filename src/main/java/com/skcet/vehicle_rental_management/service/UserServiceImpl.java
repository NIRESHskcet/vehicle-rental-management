package com.skcet.vehicle_rental_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skcet.vehicle_rental_management.model.User;
import com.skcet.vehicle_rental_management.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User createUser(User request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("username already exists");
        }
        return userRepository.save(request);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository
            .findById(id)
            .orElseThrow(()-> new RuntimeException("user not found"));

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

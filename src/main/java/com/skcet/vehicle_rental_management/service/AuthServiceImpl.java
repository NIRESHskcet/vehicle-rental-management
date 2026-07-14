package com.skcet.vehicle_rental_management.service;

import java.util.HashMap;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skcet.vehicle_rental_management.dto.request.AuthRequestDTO;
import com.skcet.vehicle_rental_management.dto.request.UserRequestDTO;
import com.skcet.vehicle_rental_management.dto.response.AuthResponseDTO;
import com.skcet.vehicle_rental_management.dto.response.UserResponseDTO;
import com.skcet.vehicle_rental_management.enums.UserRole;
import com.skcet.vehicle_rental_management.model.User;
import com.skcet.vehicle_rental_management.repository.UserRepository;
import com.skcet.vehicle_rental_management.utils.JwtUtil;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public AuthResponseDTO login(AuthRequestDTO request) {
        if(!userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("invalid credentials");
        }
        System.out.println("Username: " + request.getUsername());
        System.out.println("Password: " + request.getPassword());
        User user = userRepository.findByUsername(request.getUsername()).orElse(null);
        System.out.println("DB Password: " + user.getPassword());
        boolean matched = passwordEncoder.matches(request.getPassword(), user.getPassword());
        System.out.println("Password Matched: " + matched);
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }
        HashMap<String,String> claims = new HashMap<>();

        try {
            String token = jwtUtil.generateToken(user, claims);
            return AuthResponseDTO.builder()
                .token(token)
                .message("Login Successfull")
                .build();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public UserResponseDTO register(UserRequestDTO request) {
        if(userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("User name with ID "+ request.getUserId());
        }
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .phone(request.getPhone())
            .userId(request.getUserId())
            .role(UserRole.valueOf(request.getRole()))
            .build();

        User savedUser = userRepository.save(user);

        return UserResponseDTO.builder()
            .id(savedUser.getUserId())
            .username(savedUser.getUsername())
            .phone(savedUser.getPhone())
            .role(savedUser.getRole().name())
            .build();
    }

}

package com.skcet.vehicle_rental_management.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.skcet.vehicle_rental_management.dto.request.UserRequestDTO;
import com.skcet.vehicle_rental_management.dto.response.UserResponseDTO;
import com.skcet.vehicle_rental_management.model.User;

/**
 * UserService
 */
public interface UserService {
    UserResponseDTO createUser(UserRequestDTO request);

    List<UserResponseDTO> getAllUser();

    UserResponseDTO getUserById(Long id);

    User updateUser(Long id, User request);

    
    String deleteUser(Long id);
}

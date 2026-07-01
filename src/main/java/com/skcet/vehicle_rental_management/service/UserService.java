package com.skcet.vehicle_rental_management.service;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.skcet.vehicle_rental_management.model.User;

/**
 * UserService
 */
public interface UserService {
    User createUser(User request);

    List<User> getAllUser();

    User getUserById(Long id);

    User updateUser(Long id, User request);

    
    String deleteUser(Long id);
}

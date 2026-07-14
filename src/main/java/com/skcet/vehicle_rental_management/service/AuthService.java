package com.skcet.vehicle_rental_management.service;

import com.skcet.vehicle_rental_management.dto.request.AuthRequestDTO;
import com.skcet.vehicle_rental_management.dto.request.UserRequestDTO;
import com.skcet.vehicle_rental_management.dto.response.AuthResponseDTO;
import com.skcet.vehicle_rental_management.dto.response.UserResponseDTO;

public interface AuthService {
    AuthResponseDTO login(AuthRequestDTO request);

    UserResponseDTO register(UserRequestDTO request);
}

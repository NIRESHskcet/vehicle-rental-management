package com.skcet.vehicle_rental_management.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponseDTO {
    private Long id;
    private String username;
    private String phone;
}

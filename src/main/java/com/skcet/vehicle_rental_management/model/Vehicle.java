package com.skcet.vehicle_rental_management.model;

import com.skcet.vehicle_rental_management.enums.VehicleCondition;
import com.skcet.vehicle_rental_management.enums.VehicleType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private String vehicleNumber;
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    private String model;
    private Integer year;
    private Double rentalPrice;
    private Boolean available;
    private String location;
    @Enumerated(EnumType.STRING)
    private VehicleCondition condition;
}

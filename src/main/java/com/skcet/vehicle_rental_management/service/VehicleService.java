package com.skcet.vehicle_rental_management.service;

import java.util.List;


import com.skcet.vehicle_rental_management.model.Vehicle;

public interface VehicleService {

    Vehicle createVehicle(Vehicle request);

    List<Vehicle> getAllVehicle();

    Vehicle getVehicleById(Long id);

    Vehicle updateVehicle(Long id, Vehicle request);

    
    String deleteVehicle(Long id);

}

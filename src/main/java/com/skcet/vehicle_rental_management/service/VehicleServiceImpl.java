package com.skcet.vehicle_rental_management.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import com.skcet.vehicle_rental_management.model.Vehicle;
import com.skcet.vehicle_rental_management.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService{
    private final VehicleRepository vehicleRepo;
    @Override
    public Vehicle createVehicle(Vehicle request) {
        if(vehicleRepo.existsByVehicleNumber(request.getVehicleNumber())){
            throw new RuntimeException("vehicle number is already exists");
        }
        return vehicleRepo.save(request);
    }
    @Override
    public List<Vehicle> getAllVehicle() {
        return vehicleRepo.findAll();
    }
    @Override
    public Vehicle getVehicleById(Long id) {
        return vehicleRepo.findById(id).orElseThrow(()-> new RuntimeException("Vehicle not found"));
    }
    @Override
    public Vehicle updateVehicle(Long id, Vehicle request) {
        Vehicle vehicle = vehicleRepo.findById(id).orElseThrow(()->new RuntimeException("vehicle not found"));
        if(!vehicle.getVehicleNumber().equals(request.getVehicleNumber()) && vehicleRepo.existsByVehicleNumber(request.getVehicleNumber())){
            throw new RuntimeException("vehicle number already exists");
        }
        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setVehicleType(request.getVehicleType());
        vehicle.setModel(request.getModel());
        vehicle.setYear(request.getYear());
        vehicle.setRentalPrice(request.getRentalPrice());
        vehicle.setAvailable(request.getAvailable());
        vehicle.setLocation(request.getLocation());
        vehicle.setVehicleCondition(request.getVehicleCondition());
        
        return vehicleRepo.save(vehicle);
    }
    @Override
    public String deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepo.findById(id).orElseThrow(()->new RuntimeException("vehicle not found"));
        vehicleRepo.delete(vehicle);
        return "vehicle deleted successfully";
    }

}

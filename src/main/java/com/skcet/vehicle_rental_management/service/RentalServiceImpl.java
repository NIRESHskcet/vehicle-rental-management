package com.skcet.vehicle_rental_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skcet.vehicle_rental_management.model.Rental;
import com.skcet.vehicle_rental_management.model.Vehicle;
import com.skcet.vehicle_rental_management.repository.RentalRepository;
import com.skcet.vehicle_rental_management.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService{
    private final RentalRepository rentalRepository;
    private final VehicleRepository vehicleRepository;
    
    @Override
    public Rental createRental(Rental request) {
        if(!request.getVehicle().getAvailable()){
            throw new RuntimeException("vehicle rented");
        }
        return rentalRepository.save(request);
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id)
            .orElseThrow(()->new RuntimeException("rental is not found with id "+ id));
    }

    @Override
    public Rental updateRentals(Long id, Rental request) {
        Rental rental = rentalRepository.findById(id).orElseThrow(()->new RuntimeException("rental is not found with id" + id));
        Vehicle vehicle = vehicleRepository.findById(request.getVehicle().getVehicleId())
            .orElseThrow(()->new RuntimeException("vehicle not found"));
        rental.setVehicle(vehicle);
        rental.setCustomerName(request.getCustomerName());
        rental.setStartTime(request.getStartTime());
        rental.setEndTime(request.getEndTime());
        rental.setTotalCost(request.getTotalCost());
        rental.setRentalStatus(request.getRentalStatus());
        rental.setPickupLocation(request.getPickupLocation());
        rental.setReturnLocation(request.getReturnLocation());
        
        return rentalRepository.save(rental);
    }

    @Override
    public String deleteRentals(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(()->new RuntimeException("rental is not found with id" + id));
        rentalRepository.delete(rental);
        return "rental deleted Successfully";
    }

}

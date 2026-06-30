package com.skcet.vehicle_rental_management.service;

import java.util.List;


import com.skcet.vehicle_rental_management.model.Rental;

public interface RentalService {

    Rental createRental(Rental request);

    List<Rental> getAllRentals();

    Rental getRentalById(Long id);

    Rental updateRentals(Long id, Rental request);

    
    String deleteRentals(Long id);

}

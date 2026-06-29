package com.skcet.vehicle_rental_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skcet.vehicle_rental_management.model.Rental;


public interface RentalRepository extends JpaRepository<Rental,Long>{

}

package com.skcet.vehicle_rental_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skcet.vehicle_rental_management.model.Rental;
import com.skcet.vehicle_rental_management.service.RentalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rental")
public class RentalController {
    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<Object> createRental(@RequestBody Rental request){
        try{
            Rental rental = rentalService.createRental(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(rental);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals(){
        List<Rental> rentals = rentalService.getAllRentals();
        return ResponseEntity.status(HttpStatus.OK).body(rentals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRentalById(@PathVariable Long id){
        try {
            Rental rental = rentalService.getRentalById(id);
            return ResponseEntity.status(HttpStatus.OK).body(rental);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRentals(@PathVariable Long id,@RequestBody Rental request){
        try{
            Rental rental = rentalService.updateRentals(id,request);
            return ResponseEntity.status(HttpStatus.OK).body(rental);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRentals(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(rentalService.deleteRentals(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

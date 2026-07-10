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

import com.skcet.vehicle_rental_management.model.Vehicle;
import com.skcet.vehicle_rental_management.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
@Tag(name = "Vehicle" , description = "Provides APIs to manage vehicle information including creation, retrieval, updating, and deletion. Supports vehicle availability and rental management.")
public class VehicleController {
    private final VehicleService vehicleService;

    @Operation(method = "POST" , description = "Registers a new vehicle in the rental system after validating the vehicle details.")
    @ApiResponse(responseCode = "201",description = "Vehicle created successfully.")
    @ApiResponse(responseCode = "400",description = "Invalid vehicle details or vehicle number already exists.")
    @PostMapping
    public ResponseEntity<Object> createVehicle(@RequestBody Vehicle request){
        try{
            Vehicle vehicle = vehicleService.createVehicle(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @Operation(method = "GET",description = "Returns a list of all vehicles available in the rental system.")
    @ApiResponse(responseCode = "200" , description = "Vehicle list retrieved successfully.")
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicle(){
        List<Vehicle> vehicles = vehicleService.getAllVehicle();
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }
    @Operation(method = "GET",description = "Fetches the details of a specific vehicle using its unique identifier.")
    @ApiResponse(responseCode = "200" , description = "Vehicle retrieved successfully.")
    @GetMapping("{id}")
    public ResponseEntity<Object> getVehicleById(@PathVariable Long id){
        try{
            Vehicle vehicle = vehicleService.getVehicleById(id);
            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @Operation(method = "PUT",description = "Updates the information of an existing vehicle using its ID.")
    @ApiResponse(responseCode = "200" , description = "Vehicle updated successfully.")
    @ApiResponse(responseCode = "400" , description = "Invalid vehicle details.")
    @PutMapping("{id}")
    public ResponseEntity<Object> updateVehicle(@PathVariable Long id,@RequestBody Vehicle request){
        try{
            Vehicle vehicle = vehicleService.updateVehicle(id,request);
            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @Operation(method = "DELETE",description = "Removes a vehicle from the rental system using its unique ID.")
    @ApiResponse(responseCode = "200" , description = "Vehicle deleted successfully.")
    @ApiResponse(responseCode = "400" , description = "Vehicle not found.")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(vehicleService.deleteVehicle(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

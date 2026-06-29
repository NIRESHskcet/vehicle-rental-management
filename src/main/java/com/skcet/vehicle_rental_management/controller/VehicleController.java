package com.skcet.vehicle_rental_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skcet.vehicle_rental_management.model.Vehicle;
import com.skcet.vehicle_rental_management.service.VehicleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Object> createVehicle(@RequestBody Vehicle request){
        try{
            Vehicle vehicle = vehicleService.createVehicle(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicle(){
        List<Vehicle> vehicles = vehicleService.getAllVehicle();
        return ResponseEntity.status(HttpStatus.OK).body(vehicles);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getVehicleById(@PathVariable Long id){
        try{
            Vehicle vehicle = vehicleService.getVehicleById(id);
            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateVehicle(@PathVariable Long id,@RequestBody Vehicle request){
        try{
            Vehicle vehicle = vehicleService.updateVehicle(id,request);
            return ResponseEntity.status(HttpStatus.OK).body(vehicle);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(vehicleService.deleteVehicle(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

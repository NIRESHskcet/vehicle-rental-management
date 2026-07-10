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

import com.skcet.vehicle_rental_management.dto.request.UserRequestDTO;
import com.skcet.vehicle_rental_management.dto.response.UserResponseDTO;
import com.skcet.vehicle_rental_management.model.User;
import com.skcet.vehicle_rental_management.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User" , description = "Provides APIs to manage user registration and user information within the vehicle rental management system.")
public class UserController {
    private final UserService userService;
    @Operation(method = "POST" , description = "Creates a new user account after validating the provided information.")
    @ApiResponse(responseCode = "200" , description = "User registered successfully.")
    @ApiResponse(responseCode = "400" , description = "Invalid user details or username already exists.")
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserRequestDTO request){
        try{
            UserResponseDTO user = userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @Operation(method = "GET" , description = "Returns a list of all registered users.")
    @ApiResponse(responseCode = "200" , description = "User list retrieved successfully.")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUser(){
        List<UserResponseDTO> users = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
    @Operation(method = "GET" , description = "Fetches the details of a specific user using the user ID.")
    @ApiResponse(responseCode = "200" , description = "User retrieved successfully.")
    @ApiResponse(responseCode = "404" , description = "User not found.")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id){
        try{
            UserResponseDTO user = userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(method = "PUT" , description = "Updates the information of an existing user.")
    @ApiResponse(responseCode = "200" , description = "User updated successfully.")
    @ApiResponse(responseCode = "404" , description = "User not found.")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id,@RequestBody User request){
        try {
            User user = userService.updateUser(id,request);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @Operation(method = "DELETE" , description = "Removes a user from the vehicle rental management system.")
    @ApiResponse(responseCode = "200" , description = "User deleted successfully.")
    @ApiResponse(responseCode = "400" , description = "User not found.")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

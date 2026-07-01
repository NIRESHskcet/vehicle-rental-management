package com.skcet.vehicle_rental_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skcet.vehicle_rental_management.model.User;

/**
 * UserRepository
 */

public interface UserRepository extends JpaRepository<User,Long>{

    boolean existsByUsername(String username);

}

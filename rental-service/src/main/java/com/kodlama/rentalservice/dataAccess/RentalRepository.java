package com.kodlama.rentalservice.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlama.rentalservice.entities.Rental;

public interface RentalRepository extends JpaRepository<Rental, String> {

}

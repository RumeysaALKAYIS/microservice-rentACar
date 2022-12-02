package com.kodlama.inventoryService.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlama.inventoryService.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, String> {
	Brand findByName(String name);

	Optional<Brand> findById(int id);
}

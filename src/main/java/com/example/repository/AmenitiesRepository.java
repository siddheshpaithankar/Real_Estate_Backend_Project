package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Amenities;

@Repository
public interface AmenitiesRepository extends JpaRepository<Amenities, Integer> {
	
}

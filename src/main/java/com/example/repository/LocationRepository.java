package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> { // Use Long instead of Integer
}

package com.example.repository;

import com.example.entity.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties, Long> {
    List<Properties> findByCreatedByAdmin_Id(Integer adminId);
    
    List<Properties> findByCreatedByUser_Id(Integer userId);
    
    List<Properties> findByLocation_Id(Long locationId);
    
    List<Properties> findByOwnerNameContainingIgnoreCase(String ownerName);
    
    List<Properties> findByPriceBetween(String minPrice, String maxPrice);
}
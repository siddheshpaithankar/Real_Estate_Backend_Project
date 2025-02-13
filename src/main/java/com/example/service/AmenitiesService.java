package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Amenities;
import com.example.repository.AmenitiesRepository;

@Service
public class AmenitiesService {
    
    @Autowired
    private AmenitiesRepository amenitiesRepository;

    public Amenities saveAmenity(Amenities amenity) {
        return amenitiesRepository.save(amenity);
    }
}

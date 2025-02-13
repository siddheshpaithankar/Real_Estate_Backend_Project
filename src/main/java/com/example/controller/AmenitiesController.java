package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.Amenities;
import com.example.service.AmenitiesService;

@RestController
@RequestMapping("/api/amenities")
public class AmenitiesController {
    
    @Autowired
    private AmenitiesService amenitiesService;

    @PostMapping("/create")
    public ResponseEntity<Amenities> createAmenity(@RequestBody Amenities amenity) {
        Amenities savedAmenity = amenitiesService.saveAmenity(amenity);
        return ResponseEntity.ok(savedAmenity);
    }
}

package com.example.service;

import com.example.entity.Location;
import com.example.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // Get all locations
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    // Get location by ID
    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    // Create a new location
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    // Update an existing location
    public Location updateLocation(Long id, Location locationDetails) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            Location existingLocation = location.get();
            existingLocation.setCountry(locationDetails.getCountry());
            return locationRepository.save(existingLocation);
        }
        return null;
    }

    // Delete a location by ID
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}

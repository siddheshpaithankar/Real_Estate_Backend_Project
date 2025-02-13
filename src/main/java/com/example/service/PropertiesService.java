package com.example.service;

import com.example.entity.Admin;
import com.example.entity.Properties;
import com.example.entity.Types;
import com.example.entity.User;
import com.example.repository.AdminRepository;
import com.example.repository.PropertiesRepository;
import com.example.repository.TypesRepository;
import com.example.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService {

    @Autowired
    private PropertiesRepository propertiesRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TypesRepository typesRepository; // Injected TypesRepository

    // Create a new property
    public Properties createProperty(Properties property, Integer adminId, Integer userId, Long typeId, List<String> photos) {
        // Fetch and set Type
        Types type = typesRepository.findById(typeId)
                .orElseThrow(() -> new RuntimeException("Type not found"));
        property.setType(type);

        // Assign Admin or User
        if (adminId != null) {
            Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
            property.setCreatedByAdmin(admin);
        } else if (userId != null) {
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
            property.setCreatedByUser(user);
        }

        // Store photo URLs
        property.setPhotos(photos);

        return propertiesRepository.save(property);
    }

    // Update an existing property with photos
    public Properties updateProperty(Properties property, List<String> photos) {
        property.setPhotos(photos);
        return propertiesRepository.save(property);
    }

    // Update an existing property
    public Properties updateProperty(Properties property) {
        return propertiesRepository.save(property);
    }

    // Delete a property by ID
    public void deleteProperty(Long propertyId) {
        propertiesRepository.deleteById(propertyId);
    }

    // Filter properties by location
    public List<Properties> getPropertiesByLocation(Long locationId) {
        return propertiesRepository.findByLocation_Id(locationId);
    }
    
    
 // Method to filter properties by owner name
    public List<Properties> getPropertiesByOwnerName(String ownerName) {
        return propertiesRepository.findByOwnerNameContainingIgnoreCase(ownerName);
    }
    
    
    public List<Properties> getPropertiesByRoundedPrice(String targetPrice) {
        // Convert the target price to integer
        int target = Integer.parseInt(targetPrice);
        
        // Define a range within 300 of the target price
        int minPrice = target - 500;
        int maxPrice = target + 500;

        // Find properties within this price range
        return propertiesRepository.findByPriceBetween(String.valueOf(minPrice), String.valueOf(maxPrice));
    }

}

package com.example.controller;

import com.example.entity.Admin;
import com.example.entity.Amenities;
import com.example.entity.Location;
import com.example.entity.Properties;
import com.example.entity.User;
import com.example.repository.AdminRepository;
import com.example.repository.AmenitiesRepository;
import com.example.repository.LocationRepository;
import com.example.repository.UserRepository;
import com.example.service.PropertiesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertiesController {

    @Autowired
    private PropertiesService propertiesService;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AmenitiesRepository amenitiesRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/create")
    public ResponseEntity<Properties> createProperty(
            @RequestBody Properties property,
            @RequestParam Long locationId,
            @RequestParam List<Integer> amenitiesIds,
            @RequestParam Long typeId,
            @RequestParam List<String> photos) { // New parameter for photos

        // Get logged-in user and role
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No roles assigned"))
                .getAuthority();

        // Fetch location
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        property.setLocation(location);

        // Fetch amenities
        List<Amenities> amenities = amenitiesRepository.findAllById(amenitiesIds);
        property.setAmenities(amenities);

        // Handle property creation for Admin or User
        Integer adminId = null;
        Integer userId = null;

        if (role.equals("ROLE_ADMIN")) {
            Admin admin = adminRepository.findByUserName(loggedInUsername)
                    .orElseThrow(() -> new RuntimeException("Admin not found"));
            adminId = admin.getId();
            property.setCreatedByAdmin(admin);
        } else if (role.equals("ROLE_USER")) {
            User user = userRepository.findByEmail(loggedInUsername);
            userId = user.getId();
            property.setCreatedByUser(user);
        } else {
            throw new RuntimeException("Unauthorized role");
        }

        Properties createdProperty = propertiesService.createProperty(property, adminId, userId, typeId, photos);
        return ResponseEntity.ok(createdProperty);
    }

    @PutMapping("/update")
    public ResponseEntity<Properties> updateProperty(@RequestBody Properties property, @RequestParam List<String> photos) {
        Properties updatedProperty = propertiesService.updateProperty(property, photos);
        return ResponseEntity.ok(updatedProperty);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertiesService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<Properties>> getPropertiesByLocation(@PathVariable Long locationId) {
        List<Properties> properties = propertiesService.getPropertiesByLocation(locationId);
        return ResponseEntity.ok(properties);
    }
    
    @GetMapping("/owner/{ownerName}")
    public ResponseEntity<List<Properties>> getPropertiesByOwnerName(@PathVariable String ownerName) {
        List<Properties> properties = propertiesService.getPropertiesByOwnerName(ownerName);
        return ResponseEntity.ok(properties);
    }
    
    
    @GetMapping("/price/{price}")
    public ResponseEntity<List<Properties>> getPropertiesByPrice(@PathVariable String price) {
        // Get properties based on rounded price filter
        List<Properties> properties = propertiesService.getPropertiesByRoundedPrice(price);
        return ResponseEntity.ok(properties);
    }
    
//    @GetMapping("/count")
//    public ResponseEntity<Long> getTotalPropertiesCount() {
//        long count = propertiesService.getTotalPropertiesCount();
//        return ResponseEntity.ok(count);
//    }
//    

}

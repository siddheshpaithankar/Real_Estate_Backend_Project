package com.example.service;

import com.example.entity.ContactInfo;
import com.example.entity.Properties;
import com.example.repository.ContactInfoRepository;
import com.example.repository.PropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactInfoService {

    @Autowired
    private ContactInfoRepository contactInfoRepository;

    @Autowired
    private PropertiesRepository propertiesRepository;

    // Method to save or update the contact info and increment the view count
    public ContactInfo saveOrUpdateContactInfo(Long propertyId, String customerName, String customerEmail) {
        Properties property = propertiesRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // Check if the contact info already exists for the property and customer
        ContactInfo existingContactInfo = contactInfoRepository.findByProperty_IdAndCustomerEmail(propertyId, customerEmail);

        if (existingContactInfo != null) {
            // If exists, increment the view count
            existingContactInfo.setViewCount(existingContactInfo.getViewCount() + 1);
            return contactInfoRepository.save(existingContactInfo);
        } else {
            // If doesn't exist, create a new record
            ContactInfo newContactInfo = new ContactInfo(property, customerName, customerEmail, 1);
            return contactInfoRepository.save(newContactInfo);
        }
    }

    // Method to get contact info by property id
    public List<ContactInfo> getContactInfoByProperty(Long propertyId) {
        return contactInfoRepository.findByProperty_Id(propertyId);
    }
}

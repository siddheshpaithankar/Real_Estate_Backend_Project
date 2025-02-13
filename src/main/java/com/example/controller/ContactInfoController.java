package com.example.controller;

import com.example.entity.ContactInfo;
import com.example.service.ContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-info")
public class ContactInfoController {

    @Autowired
    private ContactInfoService contactInfoService;

    // Endpoint to save or update contact info and increment view count
    @PostMapping("/create/{propertyId}")
    public ResponseEntity<ContactInfo> createOrUpdateContactInfo(
            @PathVariable Long propertyId,
            @RequestParam String customerName,
            @RequestParam String customerEmail) {

        ContactInfo contactInfo = contactInfoService.saveOrUpdateContactInfo(propertyId, customerName, customerEmail);
        return ResponseEntity.ok(contactInfo);
    }

    // Endpoint to fetch contact info by property ID
    @GetMapping("/{propertyId}")
    public ResponseEntity<List<ContactInfo>> getContactInfoByProperty(@PathVariable Long propertyId) {
        List<ContactInfo> contactInfoList = contactInfoService.getContactInfoByProperty(propertyId);
        return ResponseEntity.ok(contactInfoList);
    }
}

package com.example.repository;

import com.example.entity.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {

    List<ContactInfo> findByProperty_Id(Long propertyId);

    // Method to find contact information by property_id and customer email
    ContactInfo findByProperty_IdAndCustomerEmail(Long propertyId, String customerEmail);
}

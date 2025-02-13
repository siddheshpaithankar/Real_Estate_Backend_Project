package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.entity.Types;
import com.example.service.TypesService;
import java.util.List;

@RestController
@RequestMapping("/api/types")
public class TypesController {

    private final TypesService typesService;

    @Autowired
    public TypesController(TypesService typesService) {
        this.typesService = typesService;
    }

    // Only ADMIN can add property types
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Types> addType(@RequestBody Types type) {
        return ResponseEntity.ok(typesService.addType(type));
    }

    // Both ADMIN and USER can view property types
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Types>> getAllTypes() {
        return ResponseEntity.ok(typesService.getAllTypes());
    }

    // Both ADMIN and USER can view a single type
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Types> getTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(typesService.getTypeById(id));
    }

    // Only ADMIN can delete a property type
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteType(@PathVariable Long id) {
        typesService.deleteType(id);
        return ResponseEntity.ok("Property type deleted successfully");
    }
}

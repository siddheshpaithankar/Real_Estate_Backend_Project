package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    //forgotpassword 
//    @PostMapping("/forgot-password/email")
//    public ResponseEntity<String> forgotPasswordByEmail(@RequestParam String email) {
//        userService.forgotPasswordByEmail(email);
//        return ResponseEntity.ok("Password reset link sent to email.");
//    }
//
//    @PostMapping("/forgot-password/phone")
//    public ResponseEntity<String> forgotPasswordByPhone(@RequestParam String phone) {
//        userService.forgotPasswordByPhone(phone);
//        return ResponseEntity.ok("Password reset link sent to phone.");
//    }
//
//    @PostMapping("/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
//        userService.resetPassword(token, newPassword);
//        return ResponseEntity.ok("Password reset successful.");
//    }
}

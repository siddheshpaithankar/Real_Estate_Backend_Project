package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

//    @Autowired
//    private EmailService emailService;  // Service for sending emails
//
//    @Autowired
//    private SmsService smsService;  // Service for sending SMS

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User updatedUser) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setAgentName(updatedUser.getAgentName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setStatus(updatedUser.isStatus());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
//    public void forgotPasswordByEmail(String email) {
//        Optional<User> userOptional = userRepository.findByEmail1(email);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            String token = UUID.randomUUID().toString();
//            user.setResetToken(token);
//            userRepository.save(user);
//
//            String resetLink = "http://localhost:8080/api/user/reset-password?token=" + token;
//            emailService.sendEmail(user.getEmail(), "Password Reset", "Click here to reset: " + resetLink);
//        } else {
//            throw new RuntimeException("User not found with this email.");
//        }
//    }
//
//    public void forgotPasswordByPhone(String phone) {
//        Optional<User> userOptional = userRepository.findByPhone(phone);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            String token = UUID.randomUUID().toString();
//            user.setResetToken(token);
//            userRepository.save(user);
//
//            String resetLink = "http://localhost:8080/api/user/reset-password?token=" + token;
//            smsService.sendSms(user.getPhone(), "Reset your password: " + resetLink);
//        } else {
//            throw new RuntimeException("User not found with this phone number.");
//        }
//    }
//
//    public void resetPassword(String token, String newPassword) {
//        Optional<User> userOptional = userRepository.findByResetToken(token);
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            user.setPassword(passwordEncoder.encode(newPassword));
//            user.setResetToken(null);
//            userRepository.save(user);
//        } else {
//            throw new RuntimeException("Invalid token.");
//        }
//    }
}

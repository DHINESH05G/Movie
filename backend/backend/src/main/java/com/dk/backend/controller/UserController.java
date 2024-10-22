package com.dk.backend.controller;

import com.dk.backend.entity.User;
import com.dk.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get user profile
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return ResponseEntity.ok(user);
    }

    // Update user profile
    @PutMapping("/profile")
    public ResponseEntity<User> updateUserProfile(@RequestBody User updatedUser, Principal principal) {
        User user = userService.updateUserProfile(principal.getName(), updatedUser);
        return ResponseEntity.ok(user);
    }

    // Delete user profile
    @DeleteMapping("/profile")
    public ResponseEntity<?> deleteUserProfile(Principal principal) {
        userService.deleteUser(principal.getName());
        return ResponseEntity.noContent().build();
    }
}


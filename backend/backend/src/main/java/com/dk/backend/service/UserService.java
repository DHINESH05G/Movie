package com.dk.backend.service;

import com.dk.backend.entity.User;
import com.dk.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User updateUserProfile(String username, User updatedUser) {
        User user = getUserByUsername(username);
        user.setEmail(updatedUser.getEmail());
        user.setFullName(updatedUser.getFullName());
        return userRepository.save(user);
    }

    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }
}
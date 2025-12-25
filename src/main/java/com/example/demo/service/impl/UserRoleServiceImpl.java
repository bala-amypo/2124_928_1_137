package com.example.demo.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Assign role to user
     */
    @Override
    public UserRole assignRole(UserRole userRole) {

        // 1️⃣ Fetch User from DB
        User user = userRepository.findById(userRole.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ Fetch Role from DB
        Role role = roleRepository.findById(userRole.getRole().getId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // 3️⃣ Set full entities
        userRole.setUser(user);
        userRole.setRole(role);

        // 4️⃣ Set assigned time (if not provided)
        if (userRole.getAssignedAt() == null) {
            userRole.setAssignedAt(LocalDateTime.now());
        }

        // 5️⃣ Save and return
        return userRoleRepository.save(userRole);
    }

    /**
     * Get UserRole by ID
     */
    @Override
    public UserRole getUserRoleById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserRole not found"));
    }

    /**
     * Delete UserRole
     */
    @Override
    public void deleteUserRole(Long id) {
        userRoleRepository.deleteById(id);
    }
}

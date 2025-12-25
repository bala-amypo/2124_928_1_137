package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.UserAccount;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Assign a role to a user
     */
    @Override
    public UserRole assignRole(UserRole userRole) {

        // Fetch full User entity
        UserAccount user = userAccountRepository
                .findById(userRole.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch full Role entity
        Role role = roleRepository
                .findById(userRole.getRole().getId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Attach entities
        userRole.setUser(user);
        userRole.setRole(role);

        // Save mapping
        return userRoleRepository.save(userRole);
    }

    /**
     * Get UserRole mapping by ID
     */
    @Override
    public UserRole getMappingById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserRole not found"));
    }

    /**
     * Get all roles for a specific user
     */
    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        return userRoleRepository.findByUser_Id(userId);
    }

    /**
     * Remove role mapping
     */
    @Override
    public void removeRole(Long id) {
        userRoleRepository.deleteById(id);
    }
}

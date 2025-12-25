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

    // ✅ ASSIGN ROLE
    @Override
    public UserRole assignRole(UserRole userRole) {

        UserAccount user = userAccountRepository
                .findById(userRole.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository
                .findById(userRole.getRole().getId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        userRole.setUser(user);
        userRole.setRole(role);

        return userRoleRepository.save(userRole);
    }

    // ✅ GET USER-ROLE BY ID
    @Override
    public UserRole getMappingById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserRole not found"));
    }

    // ✅ GET ROLES FOR USER
    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    // ✅ DELETE ROLE MAPPING
    @Override
    public void removeRole(Long id) {
        userRoleRepository.deleteById(id);
    }
}

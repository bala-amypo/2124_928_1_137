package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.UserAccount;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;

    public UserRoleServiceImpl(
            UserRoleRepository userRoleRepository,
            UserAccountRepository userAccountRepository,
            RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userAccountRepository = userAccountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRole assignRole(UserRole mapping) {

        if (mapping.getUser() == null || mapping.getUser().getId() == null) {
            throw new BadRequestException("User ID is required");
        }

        if (mapping.getRole() == null || mapping.getRole().getId() == null) {
            throw new BadRequestException("Role ID is required");
        }

        // ✅ FETCH FULL USER
        UserAccount user = userAccountRepository
                .findById(mapping.getUser().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        // ✅ FETCH FULL ROLE
        Role role = roleRepository
                .findById(mapping.getRole().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found"));

        if (!user.getActive() || !role.getActive()) {
            throw new BadRequestException("User or Role is inactive");
        }

        if (userRoleRepository.existsByUserAndRole(user, role)) {
            throw new BadRequestException("User already has this role");
        }

        mapping.setUser(user);
        mapping.setRole(role);

        return userRoleRepository.save(mapping);
    }

    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        return userRoleRepository.findByUser_Id(userId);
    }

    @Override
    public UserRole getMappingById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mapping not found"));
    }

    @Override
    public void removeRole(Long id) {
        userRoleRepository.delete(getMappingById(id));
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.entity.UserAccount;
import com.example.demo.entity.UserRole;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserAccountRepository userRepository;
    private final RoleRepository roleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository,
                               UserAccountRepository userRepository,
                               RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // ✅ Assign role to user
    @Override
    public UserRole assignRole(UserRole userRole) {

        Long userId = userRole.getUser().getId();
        Long roleId = userRole.getRole().getId();

        UserAccount user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found"));

        boolean exists = userRoleRepository
                .findByUser_Id(userId)
                .stream()
                .anyMatch(ur -> ur.getRole().getId().equals(roleId));

        if (exists) {
            throw new BadRequestException("Role already assigned to this user");
        }

        UserRole ur = new UserRole();
        ur.setUser(user);
        ur.setRole(role);

        UserRole saved = userRoleRepository.save(ur);

        // 🔥 IMPORTANT: re-fetch to avoid null values
        return userRoleRepository.findById(saved.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mapping not found"));
    }

    // ✅ Get mapping by ID
    @Override
    public UserRole getById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mapping not found"));
    }

    // ✅ Get roles of a user
    @Override
    public List<UserRole> getByUserId(Long userId) {
        return userRoleRepository.findByUser_Id(userId);
    }

    // ✅ Remove role from user
    @Override
    public void revokeRole(Long id) {
        UserRole ur = getById(id);
        userRoleRepository.delete(ur);
    }
}

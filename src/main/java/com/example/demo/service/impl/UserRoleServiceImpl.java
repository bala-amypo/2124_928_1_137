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

    public UserRoleServiceImpl(
            UserRoleRepository userRoleRepository,
            UserAccountRepository userRepository,
            RoleRepository roleRepository) {

        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /* ================= ASSIGN ROLE ================= */

    @Override
    public UserRole assignRole(UserRole userRole) {

        Long userId = userRole.getUser().getId();
        Long roleId = userRole.getRole().getId();

        // ✅ Duplicate check (required by tests)
        if (userRoleRepository.findByUserIdAndRoleId(userId, roleId).isPresent()) {
            throw new BadRequestException("Role already assigned to this user");
        }

        UserAccount user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found"));

        userRole.setUser(user);
        userRole.setRole(role);

        UserRole saved = userRoleRepository.save(userRole);

        // 🔥 Re-fetch to avoid lazy/null issues in response
        return userRoleRepository.findById(saved.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mapping not found"));
    }

    /* ================= GET BY ID ================= */

    // ✅ REQUIRED BY TESTS
    @Override
    public UserRole getMappingById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mapping not found"));
    }

    /* ================= GET BY USER ================= */

    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    /* ================= REMOVE ================= */

    @Override
    public void removeRole(Long id) {
        UserRole userRole = getMappingById(id);
        userRoleRepository.delete(userRole);
    }
}

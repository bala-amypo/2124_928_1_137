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

        UserAccount user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (!user.isActive()) {
            throw new BadRequestException("User is inactive");
        }

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found"));

        if (!role.isActive()) {
            throw new BadRequestException("Role is inactive");
        }

        if (userRoleRepository.findByUserIdAndRoleId(userId, roleId).isPresent()) {
            throw new BadRequestException("Role already assigned to this user");
        }

        UserRole mapping = new UserRole();
        mapping.setUser(user);
        mapping.setRole(role);

        return userRoleRepository.save(mapping);
    }

    /* ================= GET BY ID ================= */

    @Override
    public UserRole getMappingById(Long id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mapping not found"));
    }

    /* ================= GET BY USER ================= */

    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        // ✅ IMPORTANT: correct property path
        return userRoleRepository.findByUser_Id(userId);
    }

    /* ================= REMOVE ================= */

    @Override
    public void removeRole(Long id) {

        if (!userRoleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Mapping not found");
        }

        userRoleRepository.deleteById(id);
    }
}

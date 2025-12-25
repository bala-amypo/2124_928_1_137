package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.entity.RolePermission;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepository,
                                     RoleRepository roleRepository,
                                     PermissionRepository permissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    /* ================= CREATE ================= */

    @Override
    public RolePermission create(RolePermission rolePermission) {

        Long roleId = rolePermission.getRole().getId();
        Long permissionId = rolePermission.getPermission().getId();

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found"));

        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Permission not found"));

        boolean exists = rolePermissionRepository
                .findByRole_Id(roleId)
                .stream()
                .anyMatch(rp ->
                        rp.getPermission().getId().equals(permissionId));

        if (exists) {
            throw new BadRequestException(
                    "Permission already assigned to this role");
        }

        RolePermission rp = new RolePermission();
        rp.setRole(role);
        rp.setPermission(permission);

        RolePermission saved = rolePermissionRepository.save(rp);

        // Re-fetch to avoid lazy/null issues
        return getMappingById(saved.getId());
    }

    /* ================= GET BY ID ================= */

    // ✅ REQUIRED BY TESTS
    @Override
    public RolePermission getMappingById(Long id) {
        return rolePermissionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "RolePermission not found"));
    }

    /* ================= GET BY ROLE ================= */

    // ✅ REQUIRED BY TESTS
    @Override
    public List<RolePermission> getPermissionsForRole(Long roleId) {
        return rolePermissionRepository.findByRole_Id(roleId);
    }

    /* ================= DELETE ================= */

    @Override
    public void revokePermission(Long id) {
        RolePermission rp = getMappingById(id);
        rolePermissionRepository.delete(rp);
    }
}

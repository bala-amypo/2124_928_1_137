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

    /**
     * Assign permission to role
     */
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

        // Prevent duplicate mapping
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

        // 🔥 IMPORTANT FIX: fetch again to avoid null values
        return rolePermissionRepository.findById(saved.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "RolePermission not found"));
    }

    @Override
    public RolePermission getById(Long id) {
        return rolePermissionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "RolePermission not found"));
    }

    @Override
    public List<RolePermission> getByRoleId(Long roleId) {
        return rolePermissionRepository.findByRole_Id(roleId);
    }

    @Override
    public void delete(Long id) {
        RolePermission rp = getById(id);
        rolePermissionRepository.delete(rp);
    }
}

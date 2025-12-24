package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;
import com.example.demo.service.RolePermissionService;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;

    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @Override
    public RolePermission grantPermission(RolePermission mapping) {
        Role role = mapping.getRole();
        Permission permission = mapping.getPermission();

        if (!role.getActive() || !permission.getActive()) {
            throw new BadRequestException("Inactive role or permission");
        }

        return rolePermissionRepository.save(mapping);
    }

    @Override
    public RolePermission getMappingById(Long id) {
        return rolePermissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found"));
    }

    @Override
    public List<RolePermission> getPermissionsForRole(Long roleId) {
        return rolePermissionRepository.findByRole_Id(roleId);
    }

    @Override
    public void revokePermission(Long id) {
        rolePermissionRepository.deleteById(id);
    }
}

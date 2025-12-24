package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RolePermission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RolePermissionService;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Autowired
    public RolePermissionServiceImpl(
            RolePermissionRepository rolePermissionRepository,
            RoleRepository roleRepository,
            PermissionRepository permissionRepository
    ) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public RolePermission grantPermission(RolePermission rolePermission) {
        return rolePermissionRepository.save(rolePermission);
    }

    @Override
    public RolePermission getMappingById(Long id) {
        return rolePermissionRepository.findById(id).orElse(null);
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

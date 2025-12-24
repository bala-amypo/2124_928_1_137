package com.example.demo.service.impl;

import com.example.demo.entity.RolePermission;
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

    // ✅ EXACT constructor expected by tests
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
    public RolePermission assignPermission(RolePermission rolePermission) {
        return rolePermissionRepository.save(rolePermission);
    }

    @Override
    public List<RolePermission> getPermissionsByRoleId(Long roleId) {
        return rolePermissionRepository.findByRoleId(roleId);
    }

    // ✅ REQUIRED by interface
    @Override
    public void revokePermission(Long rolePermissionId) {
        rolePermissionRepository.deleteById(rolePermissionId);
    }
}

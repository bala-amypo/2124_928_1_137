package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.entity.RolePermission;
import com.example.demo.repository.RolePermissionRepository;
import com.example.demo.service.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository repository;

    public RolePermissionServiceImpl(RolePermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void revokePermission(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Permission> getPermissionsForRole(Long roleId) { // ✅ FIX
        return repository.findAll().stream()
                .filter(rp -> rp.getRole().getId().equals(roleId))
                .map(RolePermission::getPermission)
                .collect(Collectors.toList());
    }
}

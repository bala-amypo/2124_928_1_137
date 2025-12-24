package com.example.demo.service.impl;

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
    public RolePermission assignPermission(RolePermission rolePermission) {
        return repository.save(rolePermission);
    }

    @Override
    public RolePermission getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<RolePermission> getPermissionsForRole(Long roleId) { // ✅ FIXED
        return repository.findAll().stream()
                .filter(rp -> rp.getRole().getId().equals(roleId))
                .collect(Collectors.toList());
    }

    @Override
    public void revokePermission(Long id) {
        repository.deleteById(id);
    }
}

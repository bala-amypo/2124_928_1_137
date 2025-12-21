package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Permission;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository repository;

    public PermissionServiceImpl(PermissionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Permission createPermission(Permission permission) {
        repository.findByPermissionKey(permission.getPermissionKey()).ifPresent(p -> {
            throw new BadRequestException("Permission already exists");
        });
        return repository.save(permission);
    }

    @Override
    public Permission updatePermission(Long id, Permission permission) {
        Permission existing = getPermissionById(id);
        existing.setPermissionKey(permission.getPermissionKey());
        existing.setDescription(permission.getDescription());
        return repository.save(existing);
    }

    @Override
    public Permission getPermissionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found"));
    }

    @Override
    public List<Permission> getAllPermissions() {
        return repository.findAll();
    }

    @Override
    public void deactivatePermission(Long id) {
        Permission permission = getPermissionById(id);
        permission.setActive(false);
        repository.save(permission);
    }

    // 🔴 ADD THIS METHOD
    @Override
    public void deletePermission(Long id) {
        Permission permission = getPermissionById(id);
        repository.delete(permission);
    }
}

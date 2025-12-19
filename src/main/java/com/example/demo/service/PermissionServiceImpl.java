package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Permission;
import com.example.demo.repository.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository repository;

    @Override
    public List<Permission> getAllPermissions() {
        return repository.findAll();
    }

    @Override
    public Permission getPermissionById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Permission createPermission(Permission permission) {
        return repository.save(permission);
    }

    @Override
    public Permission updatePermission(Long id, Permission permission) {
        Optional<Permission> existing = repository.findById(id);
        if(existing.isPresent()) {
            Permission p = existing.get();
            p.setName(permission.getName());
            return repository.save(p);
        }
        return null;
    }

    @Override
    public void deletePermission(Long id) {
        repository.deleteById(id);
    }
}

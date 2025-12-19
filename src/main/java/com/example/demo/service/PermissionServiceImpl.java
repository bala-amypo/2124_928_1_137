package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Permission;
import com.example.demo.repository.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository repository;

    @Override
    public Permission addPermission(Permission permission) {
        return repository.save(permission);
    }

    @Override
    public Permission getPermission(Long id) {
        return repository.findById(id).orElse(null);
    }
}

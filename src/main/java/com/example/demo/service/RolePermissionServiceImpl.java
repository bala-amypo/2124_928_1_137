
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RolePermission;
import com.example.demo.repository.RolePermissionRepository;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionRepository repository;

    @Override
    public RolePermission grantPermission(RolePermission rp) {
        return repository.save(rp);
    }

    @Override
    public RolePermission getMapping(Long id) {
        return repository.findById(id).orElse(null);
    }
}

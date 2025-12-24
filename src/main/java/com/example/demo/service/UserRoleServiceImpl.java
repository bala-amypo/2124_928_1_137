package com.example.demo.service.impl;

import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository repository;

    // ⚠️ TEST-REQUIRED CONSTRUCTOR
    public UserRoleServiceImpl(UserRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserRole assignRole(UserRole mapping) {
        return repository.save(mapping);
    }

    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        return repository.findAll().stream()
                .filter(ur -> ur.getUser().getId().equals(userId))
                .toList();
    }

    @Override
    public UserRole getMappingById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void removeRole(Long id) {
        repository.deleteById(id);
    }
}

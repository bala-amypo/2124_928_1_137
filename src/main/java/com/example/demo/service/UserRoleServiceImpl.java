package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRoleRepository;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository repository;

    @Override
    public UserRole assignRole(UserRole userRole) {
        return repository.save(userRole);
    }

    @Override
    public UserRole getMapping(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserRole not found"));
    }
}

package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public Role createRole(Role role) {
        return repository.save(role);
    }

    @Override
    public Role getRole(Long id) {
        return repository.findById(id).orElse(null);
    }
}
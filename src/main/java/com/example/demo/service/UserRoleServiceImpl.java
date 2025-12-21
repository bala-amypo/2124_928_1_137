package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserRole;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository repository;

    public UserRoleServiceImpl(UserRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserRole assignRole(UserRole mapping) {
        if (!mapping.getUser().getActive() || !mapping.getRole().getActive()) {
            throw new BadRequestException("User or Role is inactive");
        }
        return repository.save(mapping);
    }

    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        return repository.findByUser_Id(userId);
    }

    @Override
    public UserRole getMappingById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found"));
    }

    @Override
    public void removeRole(Long id) {
        repository.delete(getMappingById(id));
    }
}

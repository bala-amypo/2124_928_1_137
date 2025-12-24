package com.example.demo.service.impl;

import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository repository;

    public UserRoleServiceImpl(UserRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserRole assign(UserRole userRole) {
        if (!userRole.getUser().getActive() || !userRole.getRole().getActive()) {
            throw new IllegalStateException("Inactive user or role");
        }
        return repository.save(userRole);
    }

    @Override
    public UserRole getMappingById(Long id) {   // ✅ FIX
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<UserRole> getByUserId(Long userId) {
        return repository.findAll().stream()
                .filter(ur -> ur.getUser().getId().equals(userId))
                .toList();
    }

    @Override
    public void removeRole(Long id) {
        repository.deleteById(id);
    }
}

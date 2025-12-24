package com.example.demo.service.impl;

import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository repository;

    public UserRoleServiceImpl(UserRoleRepository repository) {
        this.repository = repository;
    }

    // ✅ FIXED METHOD NAME (must match interface EXACTLY)
    @Override
    public UserRole assignRole(UserRole userRole) {
        if (!userRole.getUser().getActive() || !userRole.getRole().getActive()) {
            throw new IllegalStateException("Inactive user or role");
        }
        return repository.save(userRole);
    }

    @Override
    public UserRole getMappingById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        return repository.findAll().stream()
                .filter(ur -> ur.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public void removeRole(Long id) {
        repository.deleteById(id);
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.Role;
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

    @Override
    public UserRole assign(UserRole userRole) {
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
    public List<Role> getRolesForUser(Long userId) {   // ✅ FIX
        return repository.findAll().stream()
                .filter(ur -> ur.getUser().getId().equals(userId))
                .map(UserRole::getRole)
                .collect(Collectors.toList());
    }

    @Override
    public void removeRole(Long id) {
        repository.deleteById(id);
    }
}

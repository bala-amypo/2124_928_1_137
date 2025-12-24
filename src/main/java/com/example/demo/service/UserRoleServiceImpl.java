package com.example.demo.service.impl;

import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;
import org.springframework.stereotype.Service;

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
}

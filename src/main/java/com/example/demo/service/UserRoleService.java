package com.example.demo.service;

import com.example.demo.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole assignRole(UserRole userRole);

    UserRole getById(Long id);

    List<UserRole> getRolesForUser(Long userId);

    void removeRole(Long id);
}

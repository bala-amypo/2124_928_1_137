package com.example.demo.service;

import com.example.demo.entity.UserRole;

public interface UserRoleService {

    UserRole assignRole(UserRole userRole);

    UserRole getUserRoleById(Long id);

    void deleteUserRole(Long id);
}

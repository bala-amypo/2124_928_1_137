package com.example.demo.service;

import com.example.demo.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole assignRole(UserRole userRole);

    List<UserRole> getRolesByUserId(Long userId);

    // ✅ REQUIRED by compiler
    void removeRole(Long userRoleId);
}

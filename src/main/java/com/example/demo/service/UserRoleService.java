package com.example.demo.service;

import com.example.demo.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    /* ================= ASSIGN ================= */

    UserRole assignRole(UserRole userRole);

    /* ================= FETCH ================= */

    // ✅ REQUIRED BY TESTS
    UserRole getMappingById(Long id);

    // ✅ REQUIRED BY TESTS
    List<UserRole> getRolesForUser(Long userId);

    /* ================= DELETE ================= */

    void removeRole(Long id);
}

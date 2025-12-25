package com.example.demo.service;

import com.example.demo.entity.UserRole;
import java.util.List;

public interface UserRoleService {

    UserRole assignRole(UserRole userRole);

    UserRole getById(Long id);

    List<UserRole> getByUserId(Long userId);

    void revokeRole(Long id);
}

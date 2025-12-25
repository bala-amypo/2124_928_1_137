package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.UserRole;
public interface UserRoleService {

    UserRole assignRole(UserRole userRole);

    UserRole getMappingById(Long id);

    List<UserRole> getRolesForUser(Long userId);

    void removeRole(Long id);
}

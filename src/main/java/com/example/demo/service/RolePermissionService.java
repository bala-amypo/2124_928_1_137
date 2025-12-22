package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.RolePermission;

public interface RolePermissionService {

    RolePermission assignPermission(RolePermission mapping);

    RolePermission getById(Long id);

    List<RolePermission> getByRoleId(Long roleId);

    void remove(Long id);
}

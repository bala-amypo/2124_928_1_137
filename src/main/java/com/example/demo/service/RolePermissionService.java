package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.RolePermission;

public interface RolePermissionService {

    RolePermission grantPermission(RolePermission mapping);

    RolePermission getMappingById(Long id);

    List<RolePermission> getPermissionsForRole(Long roleId);

    void revokePermission(Long id);
}

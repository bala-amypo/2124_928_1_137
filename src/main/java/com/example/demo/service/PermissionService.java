package com.example.demo.service;
import com.example.demo.entity.Permission;
public interface PermissionService {
Permission addPermission(Permission permission);
Permission getPermission(Long id);
}
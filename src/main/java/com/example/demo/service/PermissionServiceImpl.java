package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Permission;
import com.example.demo.repository.PermissionRepository;
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository repo;

    public Permission addPermission(Permission permission) {
        return repo.save(permission);
    }
    public Permission getPermission(Long id) {
        return repo.findById(id).orElse(null);
    }
}

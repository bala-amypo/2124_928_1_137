package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionRepository repo;

    public RolePermission grantPermission(RolePermission rp) {
        return repo.save(rp);
    }
    public RolePermission getMapping(Long id) {
        return repo.findById(id).orElse(null);
    }
}


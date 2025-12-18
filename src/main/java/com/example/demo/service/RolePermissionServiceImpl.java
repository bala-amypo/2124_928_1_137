package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.RolePermission;
import com.example.demo.repository.RolePermissionRepository;
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
@Autowired
RolePermissionRepository repo;
public RolePermission grantPermission(RolePermission rp) {
return repo.save(rp);
}
public RolePermission getMapping(Long id) {
return repo.findById(id);
}
}
package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRoleRepository;
@Service
public class UserRoleServiceImpl implements UserRoleService {
@Autowired
UserRoleRepository repo;
public UserRole assignRole(UserRole userRole) {
return repo.save(userRole);
}
public UserRole getMapping(Long id) {
return repo.findById(id);
}
}
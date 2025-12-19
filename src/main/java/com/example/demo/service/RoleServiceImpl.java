package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
@Service
public class RoleServiceImpl implements RoleService {
@Autowired
RoleRepository repo;
public Role createRole(Role role) {
return repo.save(role);
}
public Role getRole(Long id) {
return repo.findById(id);
}
}
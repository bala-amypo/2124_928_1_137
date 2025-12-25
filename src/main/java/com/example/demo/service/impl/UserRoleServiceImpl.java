package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserRole;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleRepository userRoleRepository;
    private UserAccountRepository userAccountRepository;
    private RoleRepository roleRepository;

    // ✅ REQUIRED BY SPRING
    public UserRoleServiceImpl() {
    }

    // ✅ REQUIRED BY TEST CASE
    public UserRoleServiceImpl(
            UserRoleRepository userRoleRepository,
            UserAccountRepository userAccountRepository,
            RoleRepository roleRepository) {

        this.userRoleRepository = userRoleRepository;
        this.userAccountRepository = userAccountRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRole assignRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public UserRole getMappingById(Long id) {
        return userRoleRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserRole> getRolesForUser(Long userId) {
        return userRoleRepository.findByUser_Id(userId);
    }

    // ✅ THIS METHOD FIXES YOUR ERROR
    @Override
    public void removeRole(Long id) {
        userRoleRepository.deleteById(id);
    }
}

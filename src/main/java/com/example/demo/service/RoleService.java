package com.example.demo.service;

import com.example.demo.entity.Role;
import java.util.List;

public interface RoleService {

    Role create(Role role);

    Role getById(Long id);

    List<Role> getAll();

    Role deactivateRole(Long id);
}

package com.example.demo.repository;

import com.example.demo.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    // Existing (good to keep)
    Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);

    // Existing (can stay)
    List<UserRole> findByUserId(Long userId);

    // ✅ REQUIRED BY TESTS
    List<UserRole> findByUser_Id(Long userId);
}

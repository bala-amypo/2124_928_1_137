package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository repository;

    public UserAccountServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
    }

    /* ================= CREATE ================= */

    @Override
    public UserAccount createUser(UserAccount user) {

        String email = user.getEmail().toLowerCase();

        if (repository.existsByEmail(email)) {
            throw new BadRequestException("Email already exists");
        }

        user.setEmail(email);
        return repository.save(user);
    }

    /* ================= UPDATE ================= */

    @Override
    public UserAccount updateUser(Long id, UserAccount user) {

        UserAccount existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        // normalize email
        String newEmail = user.getEmail().toLowerCase();

        // check duplicate email (excluding current user)
        repository.findByEmail(newEmail)
                .ifPresent(found -> {
                    if (!found.getId().equals(id)) {
                        throw new BadRequestException("Email already exists");
                    }
                });

        existing.setEmail(newEmail);
        existing.setFullName(user.getFullName());

        return repository.save(existing);
    }

    /* ================= GET ================= */

    @Override
    public UserAccount getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<UserAccount> getAllUsers() {
        return repository.findAll();
    }

    /* ================= DEACTIVATE ================= */

    @Override
    public void deactivateUser(Long id) {
        UserAccount user = getUserById(id);
        user.setActive(false);
        repository.save(user);
    }
}

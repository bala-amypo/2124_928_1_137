package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;

@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    private final UserAccountService service;

    public UserAccountController(UserAccountService service) {
        this.service = service;
    }

    // POST /api/users
    @PostMapping
    public UserAccount create(@RequestBody UserAccount user) {
        return service.createUser(user);
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public UserAccount update(@PathVariable Long id, @RequestBody UserAccount user) {
        return service.updateUser(id, user);
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public UserAccount get(@PathVariable Long id) {
        return service.getUserById(id);
    }

    // GET /api/users
    @GetMapping
    public List<UserAccount> getAll() {
        return service.getAllUsers();
    }

    // PUT /api/users/{id}/deactivate
    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        service.deactivateUser(id);
    }
}

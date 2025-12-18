package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
@RestController
@RequestMapping("/api/users")
public class UserAccountController {
@Autowired
UserAccountService service;
@PostMapping("/add")
public UserAccount add(@RequestBody UserAccount user) {
return service.createUser(user);
}
@GetMapping("/get/{id}")
public UserAccount get(@PathVariable Long id) {
return service.getUser(id);
}
}
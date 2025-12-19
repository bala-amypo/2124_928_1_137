// package com.example.demo.controller;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import com.example.demo.entity.Role;
// import com.example.demo.service.RoleService;
// @RestController
// @RequestMapping("/api/roles")
// public class RoleController {
// @Autowired
// RoleService service;
// @PostMapping("/add")
// public Role add(@RequestBody Role role) {
// return service.createRole(role);
// }
// @GetMapping("/get/{id}")
// public Role get(@PathVariable Long id) {
// return service.getRole(id);
// }
//}
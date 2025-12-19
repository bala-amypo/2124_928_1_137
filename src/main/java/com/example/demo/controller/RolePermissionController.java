// package com.example.demo.controller;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import com.example.demo.entity.RolePermission;
// import com.example.demo.service.RolePermissionService;
// @RestController
// @RequestMapping("/api/role-permissions")
// public class RolePermissionController {
// @Autowired
// RolePermissionService service;
// @PostMapping("/add")
// public RolePermission add(@RequestBody RolePermission rp) {
// return service.grantPermission(rp);
// }
// @GetMapping("/get/{id}")
// public RolePermission get(@PathVariable Long id) {
// return service.getMapping(id);
// }
// }
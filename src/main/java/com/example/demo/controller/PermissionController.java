package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Permission;
import com.example.demo.service.PermissionService;
@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
@Autowired
PermissionService service;
@PostMapping("/add")
public Permission add(@RequestBody Permission permission) {
return service.addPermission(permission);
}
@GetMapping("/get/{id}")
public Permission get(@PathVariable Long id) {
return service.getPermission(id);
}
}
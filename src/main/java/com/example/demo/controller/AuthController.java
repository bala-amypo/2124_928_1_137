package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.*;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody RegisterRequestDto dto) {
        return service.register(dto);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto dto) {
        return service.login(dto);
    }
}

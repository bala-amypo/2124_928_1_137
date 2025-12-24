package com.example.demo.service;

import java.util.HashMap;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.example.demo.dto.*;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.*;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserAccountRepository repo,
                           PasswordEncoder encoder,
                           AuthenticationManager authManager,
                           JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponseDto register(RegisterRequestDto request) {
        if (repo.existsByEmail(request.getEmail()))
            throw new BadRequestException("Email already exists");

        UserAccount user = new UserAccount();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPassword(encoder.encode(request.getPassword()));
        repo.save(user);

        return new AuthResponseDto(
            jwtUtil.generateToken(new HashMap<>(), user.getEmail())
        );
    }

    public AuthResponseDto login(AuthRequestDto request) {
        UserAccount user = repo.findByEmail(request.getEmail())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword()))
            throw new BadRequestException("Invalid credentials");

        return new AuthResponseDto(
            jwtUtil.generateToken(new HashMap<>(), user.getEmail())
        );
    }
}

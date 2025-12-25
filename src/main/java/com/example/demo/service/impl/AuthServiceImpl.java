package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.entity.UserAccount;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // CONSTRUCTOR INJECTION (as per rules)
    public AuthServiceImpl(UserAccountRepository userAccountRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Register user and generate JWT token
     */
    @Override
    public AuthResponseDto register(RegisterRequestDto request) {

        // Check duplicate email
        if (userAccountRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        // Create user
        UserAccount user = new UserAccount();
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(true);

        // Save user
        userAccountRepository.save(user);

        // Generate JWT
        String token = jwtUtil.generateToken(null, user.getEmail());

        // Return token
        return new AuthResponseDto(token);
    }

    /**
     * Login user and generate JWT token
     */
    @Override
    public AuthResponseDto login(AuthRequestDto request) {

        // Fetch user
        UserAccount user = userAccountRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid email or password"));

        // Validate password
        if (!passwordEncoder.matches(
                request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }

        // Generate JWT
        String token = jwtUtil.generateToken(null, user.getEmail());

        return new AuthResponseDto(token);
    }
}

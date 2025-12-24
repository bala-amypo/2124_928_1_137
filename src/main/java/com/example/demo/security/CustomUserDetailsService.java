package com.example.demo.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserRoleRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;
    private final UserRoleRepository userRoleRepository;

    public CustomUserDetailsService(UserAccountRepository userAccountRepository,
                                    UserRoleRepository userRoleRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        UserAccount user = userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var authorities = userRoleRepository.findByUser_Id(user.getId())
                .stream()
                .map(ur -> new SimpleGrantedAuthority(ur.getRole().getRoleName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword() == null ? "" : user.getPassword(),
                authorities
        );
    }
}

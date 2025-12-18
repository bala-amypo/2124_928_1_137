package com.example.demo.service;
import com.example.demo.entity.UserAccount;
public interface UserAccountService {
UserAccount createUser(UserAccount user);
UserAccount getUser(Long id);
}
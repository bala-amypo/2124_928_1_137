package com.example.demo.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.example.demo.entity.UserAccount;

@Repository
public class UserAccountRepository {

    private Map<Long, UserAccount> users = new HashMap<>();

    public UserAccount save(UserAccount user) {
        users.put(user.getId(), user);
        return user;
    }

    public UserAccount findById(Long id) {
        return users.get(id);
    }

    public boolean existsByEmail(String email) {
        for (UserAccount u : users.values()) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}

package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserAccountRepository;
@Service
public class UserAccountServiceImpl implements UserAccountService {
@Autowired
UserAccountRepository repo;
public UserAccount createUser(UserAccount user) {
return repo.save(user);
}
public UserAccount getUser(Long id) {
return repo.findById(id);
}
}
package com.example.springboot_3_1_1.service;

import com.example.springboot_3_1_1.models.Role;
import com.example.springboot_3_1_1.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();

    void save(User user);

    User findById(int id);

    void deleteById(int id);

    User getUserByUsername(String username);

    List<Role> findAllRoles();
}

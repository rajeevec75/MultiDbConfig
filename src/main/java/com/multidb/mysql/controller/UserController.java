package com.multidb.mysql.controller;

import com.multidb.mysql.model.User;
import com.multidb.mysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // GET: /users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

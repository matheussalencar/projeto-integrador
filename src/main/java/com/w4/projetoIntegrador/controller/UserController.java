package com.w4.projetoIntegrador.controller;

import java.util.List;

import com.w4.projetoIntegrador.dtos.UserDto;
import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;



    @PostMapping("/register")
    public ResponseEntity<UserDto> createAccount(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.createUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @GetMapping("/users/email")
    public ResponseEntity<UserDto> getUser(@RequestBody String email) {
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }
}

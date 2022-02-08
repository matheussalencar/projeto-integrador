package com.w4.projetoIntegrador.controller;

import java.util.List;

import com.w4.projetoIntegrador.dtos.UserDto;
import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class UserController {
    
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserDto> createAccount(@RequestBody User user) {
        return ResponseEntity.status(201).body(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @GetMapping
    public ResponseEntity<UserDto> getUsetByEmail(@RequestBody String email) {
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }
}

package com.w4.projetoIntegrador.controller;

import java.util.List;


import com.w4.projetoIntegrador.dtos.EmailDto;
import com.w4.projetoIntegrador.dtos.UserDto;
import com.w4.projetoIntegrador.repository.UserRepository.EmailUsers;
import com.w4.projetoIntegrador.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @Autowired
    UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @GetMapping("/users/email")
    public ResponseEntity<EmailUsers> getUser(@RequestBody EmailDto mail) {
        return ResponseEntity.status(200).body(userService.getUserByEmail(mail.getEmail()));
    }
}

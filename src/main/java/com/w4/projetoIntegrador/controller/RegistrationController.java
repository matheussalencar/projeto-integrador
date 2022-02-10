package com.w4.projetoIntegrador.controller;


import javax.validation.Valid;

import com.w4.projetoIntegrador.dtos.UserDto;
import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    UserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> createAccount(@RequestBody @Valid User user)  {
        return ResponseEntity.status(201).body(userService.createUser(user));
    }
}

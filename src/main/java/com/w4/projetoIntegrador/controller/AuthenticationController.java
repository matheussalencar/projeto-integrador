package com.w4.projetoIntegrador.controller;

import com.w4.projetoIntegrador.service.TokenService;
import com.w4.projetoIntegrador.dtos.LoginRequestDto;
import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody LoginRequestDto loginRequest) {
        BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();
        User user = userRepository.findById(loginRequest.getUsername()).orElse(null);
        if (user != null && criptografar.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = tokenService.createToken(user);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}



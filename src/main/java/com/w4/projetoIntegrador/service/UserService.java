package com.w4.projetoIntegrador.service;

import java.util.List;



import com.w4.projetoIntegrador.dtos.UserDto;

import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.exceptions.BusinessException;
import com.w4.projetoIntegrador.repository.UserRepository;
import com.w4.projetoIntegrador.repository.UserRepository.EmailUsers;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();

    MailerService mailerService;

    public UserService(UserRepository userRepository, MailerService mailerService) {
        this.userRepository = userRepository;
        this.mailerService = mailerService;
    }

    public UserDto createUser(User user) {
        User userAlreadyExists = userRepository.findByEmail(user.getEmail());
        if (userAlreadyExists == null) {
            String senhaCriptografada = criptografar.encode(user.getPassword());
            user.setPassword(senhaCriptografada);
            UserDto userDto = UserDto.converteToUserDto(user);
            mailerService.sendMail(user);
            userRepository.save(user);
            return userDto;
        } else {
            throw new BusinessException("Email já cadastrado na base de dados");
        }

    }

    public List<UserDto> getAllUsers() {
        List<User> users =  userRepository.findAll();
        List<UserDto> userDtoList = UserDto.converte(users);
        return userDtoList;
    }
 
    public EmailUsers getUserByEmail(String mail) {
       UserRepository.EmailUsers listUsers = userRepository.findUserByEmailQuery(mail);
        return listUsers;
    }

}

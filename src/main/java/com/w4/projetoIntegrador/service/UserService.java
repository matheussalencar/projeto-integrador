package com.w4.projetoIntegrador.service;

import java.util.List;

import com.w4.projetoIntegrador.dtos.UserDto;
import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.exceptions.BusinessException;
import com.w4.projetoIntegrador.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(User user) {
        User userAlreadyExists = userRepository.findByEmail(user.getEmail());
        if (userAlreadyExists.equals(null)) {

            String senhaCriptografada = criptografar.encode(user.getPassword());
            user.setPassword(senhaCriptografada);
            userRepository.save(user);
            UserDto userDto = UserDto.converte(user);
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
 
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        UserDto userDto = UserDto.converte(user);
        return userDto;
    }

}

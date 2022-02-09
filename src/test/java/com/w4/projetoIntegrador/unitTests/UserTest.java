package com.w4.projetoIntegrador.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.w4.projetoIntegrador.dtos.UserDto;
import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.enums.ProfileTypes;
import com.w4.projetoIntegrador.repository.UserRepository;
import com.w4.projetoIntegrador.repository.UserRepository.EmailUsers;
import com.w4.projetoIntegrador.service.MailerService;
import com.w4.projetoIntegrador.service.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class UserTest {
    private User userValid = User.builder()
    .username("teste")
    .email("teste@gmail.com")
    .enabled(true)
    .password("akjcj43")
    .profileType(ProfileTypes.AGENT).build();

    private UserDto userDto = UserDto.builder()
    .username("teste")
    .email("teste@gmail.com")
    .profileTypes(ProfileTypes.AGENT)
    .enabled(true)
    .build();

    List<User> users = new ArrayList<>();

    @Test
    public void deveRetornarTodosOsUsuariosCadastrado() {
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        MailerService mockMailerService = Mockito.mock(MailerService.class);

        Mockito.when(mockUserRepository.findAll()).thenReturn(users);

        User userInterface = Mockito.mock(User.class);

        Mockito.when(userInterface.getUsername()).thenReturn(userValid.getUsername());
        List<User> usersTest = new ArrayList<>();
        usersTest.add(userInterface);
        UserService userService = new UserService(mockUserRepository, mockMailerService);

        List<UserDto> user = userService.getAllUsers();

        assertEquals(userDto.getUsername(), usersTest.get(0).getUsername());
    }

    @Test
    public void deveRetornarUmUsuarioComBaseNoEmail() {
        UserRepository.EmailUsers emailUsers = Mockito.mock(UserRepository.EmailUsers.class);
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        MailerService mockMailerService = Mockito.mock(MailerService.class);

        Mockito.when(mockUserRepository.findUserByEmailQuery(Mockito.anyString())).thenReturn(emailUsers);

        Mockito.when(emailUsers.getEmail()).thenReturn(userValid.getEmail());
        List<EmailUsers> usersTest = new ArrayList<>();
        usersTest.add(emailUsers);

        UserService userService = new UserService(mockUserRepository, mockMailerService);

        EmailUsers user = userService.getUserByEmail(userValid.getEmail());

        assertEquals(user.getEmail(), userValid.getEmail());
    }
}

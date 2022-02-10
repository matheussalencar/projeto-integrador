package com.w4.projetoIntegrador.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;



import com.w4.projetoIntegrador.dtos.UserDto;
import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.enums.ProfileTypes;
import com.w4.projetoIntegrador.repository.UserRepository;
import com.w4.projetoIntegrador.service.MailerService;
import com.w4.projetoIntegrador.service.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RegistrationTest {

    private UserDto userDto = UserDto.builder()
    .username("teste")
    .email("teste@gmail.com")
    .profileTypes(ProfileTypes.AGENT)
    .enabled(true)
    .build();

    private User userValid = User.builder()
    .username("teste")
    .email("teste@gmail.com")
    .enabled(true)
    .password("akjcj43")
    .profileType(ProfileTypes.AGENT)
    .build();



    @Test
    public void deveSerPossivelSeCadastrarNoSistema() {
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        MailerService mockMailerService = Mockito.mock(MailerService.class);
        UserDto mockUserDto = Mockito.mock(UserDto.class);

        Mockito.when(mockUserRepository.save(Mockito.any())).thenReturn(userValid);
        UserService userService = new UserService(mockUserRepository, mockMailerService);

        UserDto user = userService.createUser(userValid);

        assertEquals(user.getEmail(), userValid.getEmail());
    }
}

package com.w4.projetoIntegrador.unitTests;

import com.w4.projetoIntegrador.entities.User;
import com.w4.projetoIntegrador.enums.ProfileTypes;
import com.w4.projetoIntegrador.service.MailerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailTest {

    private SimpleMailMessage message = new SimpleMailMessage();
    private User user = User.builder()
            .username("teste")
            .email("teste@gmail.com")
            .enabled(true)
            .password("akjcj43")
            .profileType(ProfileTypes.AGENT).build();

    @Test
    public void deveSerPossivelEnviarUmEmail() {

        JavaMailSender javaMailSender = Mockito.mock(JavaMailSender.class);
        MailerService mailerService = Mockito.mock(MailerService.class);

        message.setFrom("noreplybootcampw4@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("Bem vindo ao sistema do grupo 11");
        message.setText("Estamos muito felizes com a sua chegada, " + user.getUsername() + "\nVoce acabou de registrar com o email: " + user.getEmail());

        mailerService.sendMail(user);

        Optional<String> msg = Arrays.stream(message.getTo()).findFirst();
        assertEquals(msg.get(), user.getEmail());
    }
}

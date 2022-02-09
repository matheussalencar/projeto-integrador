package com.w4.projetoIntegrador.service;

import com.w4.projetoIntegrador.entities.Mail;
import com.w4.projetoIntegrador.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailerService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(User user) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        
        simpleMailMessage.setFrom("noreplybootcampw4@gmail.com");
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Bem vindo ao sistema do grupo 11");
        simpleMailMessage.setText("Estamos muito felizes com a sua chegada, " + user.getUsername() + "\nVoce acabou de registrar com o email: " + user.getEmail());;

        javaMailSender.send(simpleMailMessage);
    }
}

package com.example.Tez_Yetkaz.service.email.impl;

import com.example.Tez_Yetkaz.dto.email.MessageToEmail;
import com.example.Tez_Yetkaz.service.email.IEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

    private final JavaMailSender javaMailSender;


    @Override
    public void sendMessage(MessageToEmail messageToEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(String.join(",", messageToEmail.getRecipients()));
        message.setSubject(messageToEmail.getSubject());
        message.setText(messageToEmail.getBody());
        try {
            javaMailSender.send(message);
        } catch (RuntimeException e) {
            throw new MailSendException("Sending email error");
        }
    }   
}

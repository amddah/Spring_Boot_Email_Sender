package com.example.emailsender.Service.impl;

import com.example.emailsender.Service.EmailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;

    public EmailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void SendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage =new SimpleMailMessage();

        simpleMailMessage.setFrom("portfolioabdo@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

       this.mailSender.send(simpleMailMessage);
    }
}

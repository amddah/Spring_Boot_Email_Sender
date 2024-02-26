package com.example.emailsender.controller;

import com.example.emailsender.Service.EmailSenderService;
import com.example.emailsender.resources.EmailMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EmailController {

    private final EmailSenderService emailSenderService;

    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send-mail")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage){
        this.emailSenderService.SendEmail(emailMessage.getTo(),emailMessage.getSubject(),emailMessage.getMessage());

        return ResponseEntity.ok("succes !");
    }
}

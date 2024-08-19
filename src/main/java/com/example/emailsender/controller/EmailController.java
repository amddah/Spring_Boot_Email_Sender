package com.example.emailsender.controller;

import com.example.emailsender.Service.EmailSenderService;
import com.example.emailsender.resources.EmailMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin
public class EmailController {

    private final EmailSenderService emailSenderService;

    @GetMapping("/home")
    public ModelAndView contact(){
        return new ModelAndView("contact");
    }


    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send-mail")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage){
        this.emailSenderService.SendEmail(emailMessage.getTo(),emailMessage.getSubject(),emailMessage.getMessage());

        return ResponseEntity.ok("succes !");
    }
}

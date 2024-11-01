package com.example.emailsender.controller;

import com.example.emailsender.Service.EmailSenderService;
import com.example.emailsender.Service.impl.EmailAttachmentService;
import com.example.emailsender.resources.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class EmailController {

    private final EmailSenderService emailSenderService;

    @Autowired
    private   EmailAttachmentService emailAttachmentService;
    @GetMapping("/home")
    public ModelAndView contact(){
        emailAttachmentService.downloadAttachments("imap.gmail.com","imaps","abdoamdah3@gmail.com","tbhg hdeu domk tmsh");
        return new ModelAndView("contact");
    }


    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send-mail")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage){
        this.emailSenderService.SendEmail(emailMessage.getDist(),emailMessage.getSubject(),emailMessage.getMessage());

        return ResponseEntity.ok("succes !");
    }
}

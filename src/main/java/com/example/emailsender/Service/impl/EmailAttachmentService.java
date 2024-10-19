package com.example.emailsender.Service.impl;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Service
public class EmailAttachmentService {

    private final JavaMailSender javaMailSender;

    public EmailAttachmentService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void downloadAttachments(String host, String storeType, String username, String password) {
        try {
            // Set properties
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
            Session emailSession = Session.getDefaultInstance(properties);

            // Create the IMAP store object and connect to the email server
            Store store = emailSession.getStore(storeType);
            store.connect(host, username, password);

            // Create the inbox folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // Fetch messages from the server
            Message[] messages = emailFolder.getMessages();

            for (Message message : messages) {
                if (message.getContent() instanceof MimeMultipart) {
                    MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
                    for (int i = 0; i < mimeMultipart.getCount(); i++) {
                        MimeBodyPart bodyPart = (MimeBodyPart) mimeMultipart.getBodyPart(i);
                        if (Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())) {
                            String fileName = bodyPart.getFileName();
                            File file = new File("./save" + fileName);
                            bodyPart.saveFile(file);
                        }
                    }
                }
            }

            // Close the folder and store objects
            emailFolder.close(false);
            store.close();

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}

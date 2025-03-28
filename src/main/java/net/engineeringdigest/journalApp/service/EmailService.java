package net.engineeringdigest.journalApp.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendEmail(String to, String subject, String body) {
        try {
          SimpleMailMessage mail = new SimpleMailMessage();
          mail.setTo(to);
          mail.setSubject(subject);
          mail.setText(body);
          mailSender.send(mail);
        }catch (Exception e) {
            log.error("exception while sendEmail", e);
        }
    }

}

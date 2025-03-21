package org.example.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    @Value("${mail.from.address}")
    private String fromAddress;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendAuthCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom(fromAddress);
        message.setSubject("Authentication Code");
        message.setText("Your code: " + code);
        mailSender.send(message);
    }
}

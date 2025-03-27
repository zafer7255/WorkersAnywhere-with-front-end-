package com.workersAnywhere.WorkersAnywhere.Services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UsersEmailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendVerificationMail(String to , String token){

        System.out.println("Done it" + to);

        String subject = "Verify Your Email";
        String verificationLink = "http://localhost:8080/user/verify?token=" + token;
        String body = "Click the link to verify your account: " + verificationLink;

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            System.out.println("✅ Email sent successfully to: " + to);
        } catch (Exception e) {
            System.err.println("❌ Error sending email: " + e.getMessage());
        }

    }
}

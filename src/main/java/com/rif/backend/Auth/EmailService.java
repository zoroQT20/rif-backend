package com.rif.backend.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String token) {
        String resetUrl = "http://localhost:5173/reset-password?token=" + token;
        String subject = "Password Reset Request";
        String text = "Dear User,\n\n" +
                      "We received a request to reset your password. Please click the link below to choose a new password:\n\n" +
                      resetUrl + "\n\n" +
                      "If you did not request a password reset, please ignore this email or contact support if you have any concerns.\n\n" +
                      "Best regards,\n" +
                      "ICT Interns";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}

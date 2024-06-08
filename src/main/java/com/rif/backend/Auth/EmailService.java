package com.rif.backend.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void sendPasswordResetEmail(String to, String token) {
        String resetUrl = "http://localhost:5173/reset-password?token=" + token;
        String subject = "YellowAlert - Password Reset Request";
        String text = "Dear User,\n\n" +
                      "We have received a request to reset your password. Please click the link below to choose a new password:\n\n" +
                      resetUrl + "\n\n" +
                      "If you did not request a password reset, please disregard this email or contact our support team if you have any concerns.\n\n" +
                      "Thank you,\n" +
                      "Office of Planning and Quality Management (OPQM)";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    public void sendApprovalEmail(String to, Long reportId, LocalDate approvalDate) {
        String formattedDate = approvalDate.format(formatter);
        String subject = "YellowAlert - Risk Identification Form Approval Notification";
        String text = "Dear User,\n\n" +
                      "We are pleased to inform you that your Risk Identification Form (ID: " + reportId + ") submitted on " + formattedDate + " has been approved and is now awaiting verification from the Office of Planning and Quality Management (OPQM). \n\n" +
                      "If you have any questions, please do not hesitate to contact us.\n\n" +
                      "Best regards,\n" +
                      "Office of Planning and Quality Management (OPQM)";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    public void sendRevisionEmail(String to, Long reportId, String comment, LocalDate submissionDate) {
        String formattedDate = submissionDate.format(formatter);
        String subject = "YellowAlert - Risk Identification Form Revision Required";
        String text = "Dear User,\n\n" +
                      "Your Risk Identification Form (ID: " + reportId + ") submitted on " + formattedDate + " has been reviewed and requires revision. \n\n" +
                      "Reviewer Comments: " + comment + "\n\n" +
                      "Please address the comments and resubmit your Risk Identification Form at your earliest convenience. If you have any questions, feel free to contact us.\n\n" +
                      "Thank you,\n" +
                      "Office of Planning and Quality Management (OPQM)";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    public void sendVerificationEmail(String to, Long reportId, LocalDate verificationDate) {
        String formattedDate = verificationDate.format(formatter);
        String subject = "Report Verification Notification";
        String text = "Dear User,\n\n" +
                      "We are pleased to inform you that your Risk Identification Form (ID: " + reportId + ") submitted on " + formattedDate + " has been verified by the Office of Planning and Quality Management (OPQM). \n\n" +
                      "If you have any questions, please do not hesitate to contact us.\n\n" +
                      "Best regards,\n" +
                      "Office of Planning and Quality Management (OPQM)";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}

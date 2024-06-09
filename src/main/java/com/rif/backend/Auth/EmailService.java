package com.rif.backend.Auth;

import com.rif.backend.notification.Notification;
import com.rif.backend.notification.NotificationRepository;
import com.rif.backend.Auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendApprovalEmail(String to, Long reportId, LocalDate approvalDate) {
        // Existing email sending code...

        // Create notification
        User user = userRepository.findByEmail(to).orElseThrow(() -> new RuntimeException("User not found"));
        Notification notification = new Notification(user, "Your Risk Identification Form has been approved.", LocalDateTime.now());
        notificationRepository.save(notification);
    }

    // Other email methods with similar notification creation
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

        // Create notification
        User user = userRepository.findByEmail(to).orElseThrow(() -> new RuntimeException("User not found"));
        Notification notification = new Notification(user, "Password reset email sent.", LocalDateTime.now());
        notificationRepository.save(notification);
    }

    public void sendRevisionEmail(String to, Long reportId, String comment, LocalDate submissionDate) {
        String formattedDate = submissionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

        // Create notification
        User user = userRepository.findByEmail(to).orElseThrow(() -> new RuntimeException("User not found"));
        Notification notification = new Notification(user, "Risk Identification Form requires revision.", LocalDateTime.now());
        notificationRepository.save(notification);
    }

    public void sendVerificationEmail(String to, Long reportId, LocalDate verificationDate) {
        String formattedDate = verificationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

        // Create notification
        User user = userRepository.findByEmail(to).orElseThrow(() -> new RuntimeException("User not found"));
        Notification notification = new Notification(user, "Your Risk Identification Form has been verified.", LocalDateTime.now());
        notificationRepository.save(notification);
    }
}

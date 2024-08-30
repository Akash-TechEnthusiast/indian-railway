package com.india.railway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.india.railway.model.PasswordResetToken;
import com.india.railway.model.User;
import com.india.railway.repository.PasswordResetTokenRepository;
import com.india.railway.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public String generateResetToken() {
        return UUID.randomUUID().toString();
    }

    public void processForgotPassword(String email) {
        // Check if email exists
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            // Generate token
            String token = generateResetToken();
            PasswordResetToken passwordResetToken = new PasswordResetToken(token, userOptional.get());
            passwordResetTokenRepository.save(passwordResetToken);

            // Send email
            sendResetEmail(userOptional.get(), token);
        }
    }

    private void sendResetEmail(User user, String token) {
        String resetLink = "https://localhost:9191/railway/user/reset-password?token=" + token
                + "&newPassword=akashgandham";
        // SimpleMailMessage message = new SimpleMailMessage();
        // message.setTo(user.getEmail());
        // message.setSubject("Password Reset Request");
        // message.setText("To reset your password, click the link below:\n" +
        // resetLink);

        final String username = "akash922.g@gmail.com";
        final String appPassword = "suqwdrmksaalnoac"; // Generated App Password from Gmail

        // Setting up mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        // Creating a new session with an authenticator
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, appPassword);
            }
        });
        try {
            // Creating a new email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("akash922.g@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("akashglobalconnection@gmail.com"));
            message.setSubject("Password Reset Request");
            message.setText("To reset your password, click the link below:\n" + resetLink);

            // Sending the message
            Transport.send(message);

        } catch (Exception e) {
            System.out.println("error sending email");
        }
        System.out.println("sent email");
    }

    public void resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> resetTokenOptional = passwordResetTokenRepository.findByToken(token);

        if (resetTokenOptional.isPresent()) {
            PasswordResetToken resetToken = resetTokenOptional.get();
            if (resetToken.getExpirationDate().isBefore(LocalDateTime.now())) {
                throw new IllegalStateException("Token has expired");
            }

            User user = resetToken.getUser();
            user.setPassword(newPassword);
            // user.setPassword(passwordEncoder.encode(newPassword)); // Use
            // BCryptPasswordEncoder or similar
            userRepository.save(user);

            // Optionally delete the token after reset
            passwordResetTokenRepository.delete(resetToken);

            // passwordResetTokenRepository.deleteById(resetToken.getId());
        } else {
            throw new IllegalStateException("Invalid token");
        }
    }

}

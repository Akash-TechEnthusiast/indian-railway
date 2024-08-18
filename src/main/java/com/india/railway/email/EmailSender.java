package com.india.railway.email;


import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;


@Component
public class EmailSender {

    @PostConstruct
	public void sendMail(){
	final String username = "akash922.g@gmail.com";
	final String appPassword = "vfupuukqdhkdlyed";  // Generated App Password from Gmail

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
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("akash.cloudproject@gmail.com"));
		message.setSubject("Test Email from Java App");
		message.setText("Hello, this is a test email sent from a Java application using Gmail.");

		// Sending the message
		Transport.send(message);

		System.out.println("Email sent successfully.");

	} catch (MessagingException e) {
		e.printStackTrace();
	}
  }
}
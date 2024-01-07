package com.stephen.hotelgui;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {


    public static void sendEmail(Properties config, String to, String subject, String body) {
        // Set mail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", config.getProperty("smtp.host"));
        properties.put("mail.smtp.port", config.getProperty("smtp.port"));
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Set your email credentials
        String username = config.getProperty("email.username");
        String password = config.getProperty("email.password");

        // Create a Session instance
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage
            Message message = new MimeMessage(session);
            // Set sender and recipient addresses
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            // Set email subject and body
            message.setSubject(subject);
            message.setText(body);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static Properties loadConfig() {
        Properties properties = new Properties();
        try (InputStream input = EmailSender.class.getClassLoader().getResourceAsStream("email-config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find email-config.properties");
                return properties;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
}

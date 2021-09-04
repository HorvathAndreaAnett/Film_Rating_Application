package com.example.demo.service;

import com.example.demo.model.Constants;
import com.example.demo.model.dto.RatingDTO;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class EmailService implements Observer {

    @Override
    public void update(RatingDTO ratingDTO, String email) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(Constants.host);
        mailSender.setPort(Constants.port);
        mailSender.setUsername(Constants.appEmail);
        mailSender.setPassword(Constants.emailPassword);

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        mailSender.setJavaMailProperties(properties);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(Constants.appEmail);
        message.setTo(email);
        message.setSubject("New rating created");
        message.setText(ratingDTO.toString());

        mailSender.send(message);
    }
}

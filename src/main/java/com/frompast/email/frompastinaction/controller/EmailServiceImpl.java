package com.frompast.email.frompastinaction.controller;

import com.frompast.email.frompastinaction.exception.SendMailWithAttachmentException;
import com.frompast.email.frompastinaction.service.EmailService;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath) {
        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(body);

            FileSystemResource file = new FileSystemResource(new File(attachmentPath));
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
        };

        try {
            mailSender.send(preparator);
        }
        catch (MailException ex) {
            throw new SendMailWithAttachmentException(ex);
        }
    }
}

package com.frompast.email.frompastinaction;

import com.frompast.email.frompastinaction.controller.EmailServiceImpl;
import com.frompast.email.frompastinaction.exception.SendMailWithAttachmentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmailServiceImplTest {

    private EmailServiceImpl emailService;
    private JavaMailSender mockMailSender;

    @BeforeEach
    public void setup() {
        mockMailSender = mock(JavaMailSender.class);
        emailService = new EmailServiceImpl(mockMailSender);
    }

    @Test
    void sendEmailWithAttachment_shouldSendEmailWithAttachment() {
        // Arrange
        String to = "madarat@mail.ru";
        String subject = "Test Email";
        String body = "This is a test email with attachment";
        String attachmentPath = "test.png";

        doNothing().when(mockMailSender).send(any(MimeMessagePreparator.class));

        emailService.sendEmailWithAttachment(to, subject, body, attachmentPath);

        verify(mockMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }

    @Test
    void sendEmailWithAttachment_ShouldThrowException_WhenMailSenderFails() {
        // Arrange
        String to = "madarat@mail.ru";
        String subject = "Test Email";
        String body = "This is a test email with attachment";
        String attachmentPath = "test.png";

        doThrow(MailSendException.class).when(mockMailSender).send(any(MimeMessagePreparator.class));

        assertThrows(SendMailWithAttachmentException.class, () ->
                emailService.sendEmailWithAttachment(to, subject, body, attachmentPath));
    }
}

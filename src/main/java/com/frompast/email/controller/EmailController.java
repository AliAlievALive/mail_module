package com.frompast.email.controller;

import com.frompast.email.model.EmailRequest;
import com.frompast.email.model.EmailRequestWithAttachment;
import com.frompast.email.service.EmailService;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@RestController
@RequestMapping("v1/email")
public class EmailController {
    private final EmailService emailService;
    private final MessageSource messages;

    public EmailController(EmailService emailService, MessageSource messages) {
        this.emailService = emailService;
        this.messages = messages;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest,
                                            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
        String simpleMessage = messages.getMessage("mail.simple.message", null, locale);
        simpleMessage = new String(simpleMessage.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return ResponseEntity.ok(String.format(simpleMessage, emailRequest.getTo()));
    }

    @PostMapping("/sendWithAttachment")
    public ResponseEntity<String> sendEmailWithAttachment(@RequestBody EmailRequestWithAttachment emailRequest,
                                                          @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        emailService.sendEmailWithAttachment(emailRequest.getTo(), emailRequest.getSubject(),
                emailRequest.getBody(), emailRequest.getAttachmentPath());
        String attachmentMessage = messages.getMessage("mail.attachment.message", null, locale);
        attachmentMessage = new String(attachmentMessage.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return ResponseEntity.ok(String.format(attachmentMessage, emailRequest.getTo()));
    }
}

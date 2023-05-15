package com.frompast.email.frompastinaction.controller;

import com.frompast.email.frompastinaction.model.EmailRequest;
import com.frompast.email.frompastinaction.model.EmailRequestWithAttachment;
import com.frompast.email.frompastinaction.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
        return ResponseEntity.ok("Email sent successfully");
    }

    @PostMapping("/sendWithAttachment")
    public ResponseEntity<String> sendEmailWithAttachment(@RequestBody EmailRequestWithAttachment emailRequest) {
        emailService.sendEmailWithAttachment(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody(), emailRequest.getAttachmentPath());
        return ResponseEntity.ok("Email with attachment sent successfully");
    }
}

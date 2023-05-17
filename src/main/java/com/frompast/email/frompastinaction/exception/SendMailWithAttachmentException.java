package com.frompast.email.frompastinaction.exception;

import org.springframework.mail.MailException;

public class SendMailWithAttachmentException extends RuntimeException {
    public SendMailWithAttachmentException(MailException e) {
        super(e);
    }
}

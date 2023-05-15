package com.frompast.email.frompastinaction.exception;

import jakarta.mail.MessagingException;

public class SendMailWithAttachmentException extends RuntimeException {
    public SendMailWithAttachmentException(MessagingException e) {
        super(e);
    }
}

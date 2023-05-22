package com.frompast.email.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public class EmailRequestWithAttachment extends EmailRequest{

    private String attachmentPath;

    public EmailRequestWithAttachment(String to, String subject, String body) {
        super(to, subject, body);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        EmailRequestWithAttachment that = (EmailRequestWithAttachment) o;

        return Objects.equals(attachmentPath, that.attachmentPath);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (attachmentPath != null ? attachmentPath.hashCode() : 0);
        return result;
    }
}

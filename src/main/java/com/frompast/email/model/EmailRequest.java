package com.frompast.email.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmailRequest {
    private final String to;
    private final String subject;
    private final String body;
}

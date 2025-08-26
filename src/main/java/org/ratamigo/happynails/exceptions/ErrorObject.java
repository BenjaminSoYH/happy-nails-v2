package org.ratamigo.happynails.exceptions;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorObject {
    private int statusCode;
    private String message;
    private Date timeStamp;
}

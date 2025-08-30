package org.ratamigo.happynails.exceptions;

public class AppointmentNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}

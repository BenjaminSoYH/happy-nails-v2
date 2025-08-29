package org.ratamigo.happynails.appointments.exceptions;

public class AppointmentNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}

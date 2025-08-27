package org.ratamigo.happynails.exceptions;

public class AvailabilityNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public AvailabilityNotFoundException(String message) {
        super(message);
    }
}

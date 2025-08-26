package org.ratamigo.happynails.exceptions;

public class ServiceTypeNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public ServiceTypeNotFoundException(String message) {
        super(message);
    }
}

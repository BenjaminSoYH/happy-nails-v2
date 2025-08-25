package org.ratamigo.happynails.exceptions;

public class NailTechNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1;

    public NailTechNotFoundException(String message) {
        super(message);
    }
}

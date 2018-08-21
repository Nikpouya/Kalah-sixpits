package com.kalahgame.sixpits.exceptions;

import javax.management.InvalidAttributeValueException;

public class InvalidIdentifierException extends InvalidAttributeValueException {

    public InvalidIdentifierException(String message)
    {
        super(message);
    }
}

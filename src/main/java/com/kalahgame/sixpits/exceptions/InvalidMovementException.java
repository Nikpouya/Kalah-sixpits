package com.kalahgame.sixpits.exceptions;

import javax.management.InvalidAttributeValueException;

public class InvalidMovementException extends InvalidAttributeValueException {

    public InvalidMovementException(String message)
    {
        super(message);
    }
}

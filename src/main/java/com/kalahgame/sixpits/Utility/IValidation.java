package com.kalahgame.sixpits.Utility;

import com.kalahgame.sixpits.exceptions.InvalidIdentifierException;
import com.kalahgame.sixpits.exceptions.InvalidMovementException;
import com.kalahgame.sixpits.model.entities.GameBoard;
import com.kalahgame.sixpits.model.entities.Pit;

public interface IValidation {
    void validateIdentifier(String pitId) throws InvalidIdentifierException;
    void validatePit(final Pit currentPit) throws InvalidMovementException;
    void validateGame(final GameBoard gameBoard);
}

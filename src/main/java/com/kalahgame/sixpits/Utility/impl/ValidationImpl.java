package com.kalahgame.sixpits.Utility.impl;

import com.kalahgame.sixpits.Utility.IValidation;
import com.kalahgame.sixpits.exceptions.InvalidGameException;
import com.kalahgame.sixpits.exceptions.InvalidIdentifierException;
import com.kalahgame.sixpits.exceptions.InvalidMovementException;
import com.kalahgame.sixpits.exceptions.ResourceNotFoundException;
import com.kalahgame.sixpits.model.entities.GameBoard;
import com.kalahgame.sixpits.model.entities.Pit;
import com.kalahgame.sixpits.model.entities.enums.GameStatusE;

public class ValidationImpl implements IValidation {

    public final void validateIdentifier(String pitId) throws InvalidIdentifierException {
        if (!pitId.matches("[1-6]"))
            throw new InvalidIdentifierException("The pit identifier is invalid. Pit ID range is between 1 to 6 (and not null)");
    }

    public final void validatePit(final Pit currentPit) throws InvalidMovementException {
        if (currentPit.getStones() == 0)
            throw new InvalidMovementException("The selected pit is empty, try another pit");
    }

    public final void validateGame(final GameBoard gameBoard) {
        if(gameBoard == null)
            throw new ResourceNotFoundException("The game board ID is not valid");
        else if (!gameBoard.getGameStatus().equals(GameStatusE.NOT_STARTED) && !gameBoard.getGameStatus().equals(GameStatusE.PLAYING))
            throw new InvalidGameException("This game has already a winner - " + gameBoard.getGameStatus());
    }
}

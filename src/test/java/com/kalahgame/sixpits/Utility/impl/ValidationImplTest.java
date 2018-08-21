package com.kalahgame.sixpits.Utility.impl;

import com.kalahgame.sixpits.SixpitsApplication;
import com.kalahgame.sixpits.Utility.IValidation;
import com.kalahgame.sixpits.exceptions.InvalidGameException;
import com.kalahgame.sixpits.exceptions.InvalidIdentifierException;
import com.kalahgame.sixpits.exceptions.InvalidMovementException;
import com.kalahgame.sixpits.exceptions.ResourceNotFoundException;
import com.kalahgame.sixpits.model.entities.GameBoard;
import com.kalahgame.sixpits.model.entities.Pit;
import com.kalahgame.sixpits.model.entities.enums.GameStatusE;
import com.kalahgame.sixpits.model.entities.enums.PitE;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SixpitsApplication.class)
@ActiveProfiles("VTest")
public class ValidationImplTest {
    private IValidation validation;

    // void validateIdentifier(String pitId)
    @Test
    public void ShouldPassIdentifierValidationWithoutException() throws Exception {
        validation = new ValidationImpl();
        for(int i=1; i<7; i ++)
            validation.validateIdentifier(String.valueOf(i));
    }

    // void validateIdentifier(String pitId)
    @Test(expected = InvalidIdentifierException.class)
    public void ShouldThrowsInvalidIdentifierException() throws Exception {
        validation = new ValidationImpl();
        for(int i=0; i<8; i +=7)
        validation.validateIdentifier(String.valueOf(i));
    }

    // void validatePit(final Pit currentPit)
    @Test
    public void ShouldPassPitValidationWithoutException() throws Exception {
        Pit pit = new Pit(PitE.ONE);
        new ValidationImpl().validatePit(pit);
    }

    // void validatePit(final Pit currentPit)
    @Test(expected = InvalidMovementException.class)
    public void ShouldThrowsInvalidMovementException() throws Exception {
        Pit pit = new Pit(PitE.ONE);
        pit.setStones(0);
        new ValidationImpl().validatePit(pit);
    }

    // void validateGame(final GameBoard gameBoard)
    @Test
    public void ShouldPassGameValidationWithoutException() throws Exception {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setGameStatus(GameStatusE.PLAYING);
        new ValidationImpl().validateGame(gameBoard);
    }

    // void validateGame(final GameBoard gameBoard)
    @Test(expected = InvalidGameException.class)
    public void ShouldThrowsIvalidGameException() throws Exception {
        GameBoard gameBoard = new GameBoard();
        gameBoard.setGameStatus(GameStatusE.DRAW);
        new ValidationImpl().validateGame(gameBoard);
    }

    // void validateGame(final GameBoard gameBoard)
    @Test(expected = ResourceNotFoundException.class)
    public void ShouldThrowsResourceNotFoundException() throws Exception {
        new ValidationImpl().validateGame(null);
    }

}
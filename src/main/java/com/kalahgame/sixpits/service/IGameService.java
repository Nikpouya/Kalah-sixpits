package com.kalahgame.sixpits.service;

import com.kalahgame.sixpits.exceptions.InvalidMovementException;
import com.kalahgame.sixpits.model.entities.GameBoard;
import com.kalahgame.sixpits.model.entities.enums.PitE;

public interface IGameService {

    GameBoard AddGame();
    GameBoard Move(String gameBoardId, PitE pitId) throws InvalidMovementException;
}
package com.kalahgame.sixpits.model.repository;

import com.kalahgame.sixpits.model.entities.GameBoard;

public interface IGameRepository {

    GameBoard save(GameBoard gameBoard);
    GameBoard findById(String id);
}

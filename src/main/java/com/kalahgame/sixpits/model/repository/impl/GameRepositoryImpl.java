package com.kalahgame.sixpits.model.repository.impl;

import com.kalahgame.sixpits.model.entities.GameBoard;
import com.kalahgame.sixpits.model.repository.IGameRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameRepositoryImpl implements IGameRepository {
    private final static Logger logger = Logger.getLogger(GameRepositoryImpl.class);
    private static final ConcurrentHashMap<String,GameBoard> gameBoards = new ConcurrentHashMap<>();
    @Override
    public GameBoard save(GameBoard gameBoard) {
        gameBoards.putIfAbsent(gameBoard.getId(),gameBoard);
        logger.info("Game board is ready");
        return gameBoard;
    }

    @Override
    public GameBoard findById(String id) {
        return gameBoards.get(id);
    }

}
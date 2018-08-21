package com.kalahgame.sixpits.service.impl;

import com.kalahgame.sixpits.Utility.ILogic;
import com.kalahgame.sixpits.Utility.impl.LogicImpl;
import com.kalahgame.sixpits.Utility.impl.ValidationImpl;
import com.kalahgame.sixpits.exceptions.InvalidMovementException;
import com.kalahgame.sixpits.model.entities.GameBoard;
import com.kalahgame.sixpits.model.entities.Kalah;
import com.kalahgame.sixpits.model.entities.Pit;
import com.kalahgame.sixpits.model.entities.PlaySection;
import com.kalahgame.sixpits.model.entities.enums.GameStatusE;
import com.kalahgame.sixpits.model.entities.enums.PitE;
import com.kalahgame.sixpits.model.entities.enums.SectionE;
import com.kalahgame.sixpits.model.repository.IGameRepository;
import com.kalahgame.sixpits.service.IGameService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    IGameRepository gameRepository;

    private static final int PITS_INIT_NUMBER = 6;
    private ILogic logic = null;
    private final static Logger logger = Logger.getLogger(GameServiceImpl.class);
    /**
     * @param gameBoardId ID of existing game board to retrieve it
     * @param pitId ID of selected pit to distribute its stones
     * @return Kalah game board
     * @throws InvalidMovementException When the selected pit is empty
     */
    @Override
    public GameBoard Move(String gameBoardId, PitE pitId) throws InvalidMovementException {

            final GameBoard gameBoard = gameRepository.findById(gameBoardId);
            new ValidationImpl().validateGame(gameBoard);
            final PlaySection currentSection = getCurrentSection(gameBoard);
            final Pit currentPit = currentSection.getPits().get(pitId.getPosition()-1);
            new ValidationImpl().validatePit(currentPit);
            Play(gameBoard, currentPit, currentSection);
            logger.info(String.valueOf(currentSection.getId()).concat(" player distributed stones from pit number ".concat(String.valueOf(pitId.getPosition()))));
            return gameBoard;
    }

    @Override
    public GameBoard AddGame() {
        return gameRepository.save(new GameBoard());
    }

    /**
     * @param gameBoard Kalah game board
     * @param currentPit User selected pit to distribute its stones
     * @param currentSection The section which belongs to the current player
     */
    private void Play(final GameBoard gameBoard, final Pit currentPit, final PlaySection currentSection) {
        logic = new LogicImpl();
        gameBoard.setGameStatus(GameStatusE.PLAYING);
        int stones = currentPit.getStones();
        int position = currentPit.getId().getPosition();
        currentPit.setStones(0);
        gameBoard.setCurrentSection(getOpponentSection(currentSection.getId()));

        SectionE currentSectionSide = currentSection.getId();

        while (stones > 0) {
            stones = distributeStones(position, stones, gameBoard, currentSection.getId(), currentSectionSide);
            position = 0;
            currentSectionSide = getOpponentSection(currentSectionSide);
        }
        UpdateGameStatus(gameBoard);
    }

    /**
     * @param position Current pit position
     * @param stones Number of stones in the selected pit
     * @param gameBoard Kalah game board
     * @param currentSection The section which belongs to the current player
     * @param currentSectionSide The section where current player plays on (it also can be opponent side)
     * @return The number of remained stones
     */
    private int distributeStones(int position, int stones, GameBoard gameBoard, final SectionE currentSection, final SectionE currentSectionSide) {

        PlaySection playSection = getCurrentSectionSide(gameBoard, currentSectionSide);

        for (int pitId = position; pitId < PITS_INIT_NUMBER; pitId++) {
            if (stones > 0) {
                Pit pit = playSection.getPits().get(pitId);
                pit.setStones(pit.getStones()+1);
                stones--;

                if (logic.isEmptyKalah(stones, currentSection, playSection.getId(), pit)) {
                    logic.emptyPit(gameBoard, currentSection, pit.getId().getPosition());
                }
            } else {
                return 0;
            }
        }

        if (logic.isKalah(stones, currentSection, playSection.getId())) {
            addStoneKalah(playSection, 1);

            stones--;

            if (logic.isExtraMove(stones, currentSection, playSection.getId())) {
                logic.extraMove(gameBoard, currentSection);
            }
        }

        return stones;
    }

    /**
     * @param gameBoard Kalah game board
     */
    private final void UpdateGameStatus(final GameBoard gameBoard) {
        int stonesSectionOne = gameBoard.getFirstSection().getPits().stream().mapToInt(Pit::getStones).sum();
        int stonesSectionTwo = gameBoard.getSecondSection().getPits().stream().mapToInt(Pit::getStones).sum();

        if (stonesSectionOne == 0 || stonesSectionTwo == 0) {
            gameBoard.getFirstSection().getPits().forEach(pit -> pit.setStones(0));
            gameBoard.getSecondSection().getPits().forEach(pit -> pit.setStones(0));

            addStoneKalah(gameBoard.getFirstSection(), stonesSectionOne);
            addStoneKalah(gameBoard.getSecondSection(), stonesSectionTwo);

            if (gameBoard.getFirstSection().getKalah().getStones() > gameBoard.getSecondSection().getKalah().getStones())
                gameBoard.setGameStatus(GameStatusE.FIRST_PLAYER_WON);
            else if (gameBoard.getFirstSection().getKalah().getStones() < gameBoard.getSecondSection().getKalah().getStones())
                gameBoard.setGameStatus(GameStatusE.SECOND_PLAYER_WON);
            else
                gameBoard.setGameStatus(GameStatusE.DRAW);
            logger.info("Winner of the game: ".concat(String.valueOf(gameBoard.getGameStatus())));
        }
    }

    /**
     * @param playSection The section which belongs to the current player
     * @param stone Number of stones to be added to the current player's Kalah
     */
    private void addStoneKalah(final PlaySection playSection, int stone) {
        Kalah kalah = playSection.getKalah();
        kalah.setStones(kalah.getStones() + stone);
    }

    /**
     * @param gameBoard Kalah game board
     * @return The section which belongs to the current player
     */
    private PlaySection getCurrentSection(final GameBoard gameBoard) {
        return gameBoard.getCurrentSection() == SectionE.FIRST ? gameBoard.getFirstSection() : gameBoard.getSecondSection();
    }

    /**
     * @param gameBoard Kalah game board
     * @param currentSectionSide The section where current player plays on (it also can be opponent side)
     * @return The section where current player continues distributing of stones on it
     */
    private PlaySection getCurrentSectionSide(final GameBoard gameBoard, final SectionE currentSectionSide) {
        return gameBoard.getFirstSection().getId() == currentSectionSide ? gameBoard.getFirstSection() : gameBoard.getSecondSection();
    }

    /**
     * @param currentSection The section which belongs to the current player
     * @return The section which belongs to the opponent player
     */
    private SectionE getOpponentSection(final SectionE currentSection) {
        return currentSection == SectionE.FIRST ? SectionE.SECOND : SectionE.FIRST;
    }

}
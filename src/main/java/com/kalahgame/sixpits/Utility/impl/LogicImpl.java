package com.kalahgame.sixpits.Utility.impl;

import com.kalahgame.sixpits.Utility.ILogic;
import com.kalahgame.sixpits.model.entities.GameBoard;
import com.kalahgame.sixpits.model.entities.Kalah;
import com.kalahgame.sixpits.model.entities.Pit;
import com.kalahgame.sixpits.model.entities.PlaySection;
import com.kalahgame.sixpits.model.entities.enums.SectionE;

import java.util.List;

public class LogicImpl implements ILogic {

    private static final int PITS_INIT_NUMBER = 6;

    public final void emptyPit(final GameBoard gameBoard, final SectionE currentSection, int position) {
        PlaySection section = null;
        int opponentStones = 0;
        int oppositePosition = (PITS_INIT_NUMBER - 1) - position;

        if (currentSection == SectionE.FIRST) {
            List<Pit> pitsSectionTwo = gameBoard.getSecondSection().getPits();

            section = gameBoard.getFirstSection();
            opponentStones = pitsSectionTwo.get(oppositePosition).getStones();
            pitsSectionTwo.get(oppositePosition).setStones(0);
        } else {
            List<Pit> pitsSectionOne = gameBoard.getFirstSection().getPits();

            section = gameBoard.getSecondSection();
            opponentStones = pitsSectionOne.get(oppositePosition).getStones();
            pitsSectionOne.get(oppositePosition).setStones(0);
        }

        this.addStoneKalah(section, (opponentStones + 1));
        section.getPits().get(position).setStones(0);
    }

    private void addStoneKalah(final PlaySection playSection, int stone) {
        Kalah kalah = playSection.getKalah();
        kalah.setStones(kalah.getStones() + stone);
    }

    public final void extraMove(final GameBoard board, final SectionE currentSection) {
        board.setCurrentSection(currentSection);
    }

    public final boolean isKalah(int stones, SectionE currentSection, SectionE section) {
        return stones > 0 && section == currentSection;
    }

    public final boolean isExtraMove(int stones, SectionE currentSection, SectionE section) {
        return stones == 0 && section == currentSection;
    }

    public final boolean isEmptyKalah(int stones, SectionE currentSection, SectionE section, Pit pit) {
        return stones == 0 && section == currentSection && pit.getStones() == 1;
    }
}

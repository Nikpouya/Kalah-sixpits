package com.kalahgame.sixpits.Utility;

import com.kalahgame.sixpits.model.entities.GameBoard;
import com.kalahgame.sixpits.model.entities.Pit;
import com.kalahgame.sixpits.model.entities.PlaySection;
import com.kalahgame.sixpits.model.entities.enums.SectionE;

public interface ILogic {
    void emptyPit(final GameBoard gameBoard, final SectionE currentSection, int position);
    void extraMove(final GameBoard board, final SectionE currentSection);
    boolean isKalah(int stones, SectionE currentSection, SectionE section);
    boolean isExtraMove(int stones, SectionE currentSection, SectionE section);
    boolean isEmptyKalah(int stones, SectionE currentSection, SectionE section, Pit pit);
}

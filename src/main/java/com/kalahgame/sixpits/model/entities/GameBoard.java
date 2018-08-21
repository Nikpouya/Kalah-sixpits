package com.kalahgame.sixpits.model.entities;

import com.kalahgame.sixpits.model.entities.enums.GameStatusE;
import com.kalahgame.sixpits.model.entities.enums.SectionE;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameBoard{

    private String id;
    @ApiModelProperty(notes = "The section where current player should distribute stones from")
    private SectionE currentSection;
    @ApiModelProperty(notes = "The section which belongs to the first player")
    private PlaySection firstSection;
    @ApiModelProperty(notes = "The section which belongs to the second player")
    private PlaySection secondSection;
    @ApiModelProperty(notes = "The status of current game")
    private GameStatusE gameStatus;

    public GameBoard() {
        this.id = UUID.randomUUID().toString();
        this.currentSection = SectionE.FIRST;
        this.firstSection = new PlaySection(SectionE.FIRST);
        this.secondSection = new PlaySection(SectionE.SECOND);
        this.gameStatus = GameStatusE.NOT_STARTED;
    }

    public List<PlaySection> getBothSections()
    {
        List<PlaySection> bothSections = new ArrayList<>();
        bothSections.add(getFirstSection());
        bothSections.add(getSecondSection());
        return bothSections;
    }

    public String getId() {
        return id;
    }

    public SectionE getCurrentSection() {
        return currentSection;
    }

    public void setCurrentSection(SectionE currentSection) {
        this.currentSection = currentSection;
    }

    public PlaySection getFirstSection() {
        return firstSection;
    }

    public PlaySection getSecondSection() {
        return secondSection;
    }

    public GameStatusE getGameStatus() { return gameStatus; }

    public void setGameStatus(GameStatusE gameStatus) {
        this.gameStatus = gameStatus;
    }

}

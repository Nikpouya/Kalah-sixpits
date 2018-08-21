package com.kalahgame.sixpits.model.entities;

import com.kalahgame.sixpits.model.entities.enums.PitE;
import io.swagger.annotations.ApiModelProperty;

public class Pit {

    private PitE id;
    @ApiModelProperty(notes = "The number of stones in each pit")
    private int stones;

    public Pit(PitE id) {
        this.id = id;
        this.stones = 6;
    }

    public int getStones() { return stones; }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public PitE getId() {
        return id;
    }
}

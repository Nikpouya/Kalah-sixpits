package com.kalahgame.sixpits.model.entities;

import io.swagger.annotations.ApiModelProperty;

public class Kalah
{

    @ApiModelProperty(notes = "The number of stones in player's Kalah")
    private int stones;

    public Kalah(int stones) {
        this.stones = stones;
    }

    public int getStones() {
        return stones;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }
}

package com.kalahgame.sixpits.model.entities.enums;

import java.util.stream.Stream;

public enum PitE {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6);

    private int position;

    PitE(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public static PitE get(int position) {
        return Stream.of(PitE.values())
                .filter(t-> t.getPosition() == position)
                .findFirst().orElse(null);
    }
}

package com.kalahgame.sixpits.model.entities;


import com.kalahgame.sixpits.model.entities.enums.PitE;
import com.kalahgame.sixpits.model.entities.enums.SectionE;
import io.swagger.annotations.ApiModelProperty;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class PlaySection {

    private SectionE id;
    @ApiModelProperty(notes = "List of pits in each section")
    private List<Pit> pits = new LinkedList<>();
    @ApiModelProperty(notes = "Playing section's Kalah")
    private Kalah kalah;

    public PlaySection(SectionE id) {
        this.id = id;
        Stream.of(PitE.values()).forEach(t->pits.add(new Pit(t)));
        this.kalah = new Kalah(0) ;
    }

    public SectionE getId() { return id; }

    public List<Pit> getPits() {
        return pits;
    }

    public Kalah getKalah() {
        return kalah;
    }


}

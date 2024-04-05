package com.nisbeterik.chunkr.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LeitnerBox {

    private List<List<Chunk>> levels;

    private final UUID BOX_ID;

    private String name;

    private final int NUM_LEVELS = 7;

    public LeitnerBox(String name) {
        this.name = name;
        this.BOX_ID = UUID.fromString(name);
        for(int i = 0; i<=NUM_LEVELS; i++) {
            levels.add(new ArrayList<>());
        }

    }
    @JsonCreator
    public LeitnerBox(
            @JsonProperty("BOX_ID") UUID boxId,
            @JsonProperty("name") String name)
    {
        this.BOX_ID = boxId;
        this.name = name;
    }


    public UUID getBOX_ID() {return this.BOX_ID;}

    public void addChunk(Chunk chunk) {
        levels.get(Levels.LEVEL_1.ordinal()).add(chunk);
    }

    public void moveToNextLevel(Chunk chunk) {
        int currentLevel = chunk.getLevel();
        if(currentLevel<NUM_LEVELS-1) {
            chunk.setLevel(currentLevel+1);
        }
    }

    public void resetLevel(Chunk chunk) {
        chunk.setLevel(Levels.LEVEL_1.ordinal());
    }
}

package com.nisbeterik.chunkr.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LeitnerBox {

    private List<List<Chunk>> levels;

    private final UUID BOX_ID;

    private final String name;

    private final int NUM_LEVELS = 7;

    public LeitnerBox(String name) {
        this.name = name;
        this.BOX_ID = UUID.randomUUID();
        this.levels = initializeLevels();

    }
    @JsonCreator
    public LeitnerBox(@JsonProperty("box_ID") UUID boxId,
                      @JsonProperty("name") String name,
                      @JsonProperty("levels") List<List<Chunk>> levels) {
        this.BOX_ID = boxId;
        this.name = name;
        this.levels = levels == null ? initializeLevels() : levels;
    }




    public UUID getBOX_ID() {
        return BOX_ID;
    }


    public String getName() {
        return name;
    }


    public List<List<Chunk>> getLevels() {
        return levels;
    }

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

    private List<List<Chunk>> initializeLevels() {
        List<List<Chunk>> newLevels = new ArrayList<>();
        for (int i = 0; i <= NUM_LEVELS; i++) {
            newLevels.add(new ArrayList<>());
        }
        return newLevels;
    }
}

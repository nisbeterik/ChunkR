package com.nisbeterik.chunkr.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LeitnerBox {

    private final int MAX_STAGE = 64;

    private List<List<Chunk>> levels;

    private final UUID BOX_ID;

    private final String name;

    private final int NUM_LEVELS = 7;

    private int currentStage;

    public LeitnerBox(String name) {
        this.name = name;
        this.BOX_ID = UUID.randomUUID();
        this.levels = initializeLevels();
        this.currentStage = 1;

    }
    @JsonCreator
    public LeitnerBox(@JsonProperty("box_ID") UUID boxId,
                      @JsonProperty("name") String name,
                      @JsonProperty("levels") List<List<Chunk>> levels) {
        this.BOX_ID = boxId;
        this.name = name;
        this.levels = levels == null ? initializeLevels() : levels;
        this.currentStage = currentStage;
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

    public int getCurrentStage() {
        return currentStage;
    }

    public void addChunk(Chunk chunk) {
        levels.get(Levels.LEVEL_1.ordinal()).add(chunk);
    }

    public void moveToNextLevel(Chunk chunk) {
        int currentLevel = chunk.getLevel();
        if (currentLevel >= 0 && currentLevel < NUM_LEVELS - 1) {
            levels.get(currentLevel).remove(chunk);
            chunk.setLevel(currentLevel + 1);
            levels.get(currentLevel + 1).add(chunk);
        }
    }

    public void resetLevel(Chunk chunk) {
        int currentLevel = chunk.getLevel();
        if (currentLevel > Levels.LEVEL_1.ordinal() && currentLevel < NUM_LEVELS - 1) {
            levels.get(currentLevel).remove(chunk);
            chunk.setLevel(Levels.LEVEL_1.ordinal());
            levels.get(Levels.LEVEL_1.ordinal()).add(chunk);
        }


    }

    public List<Chunk> getLevelList(int level) {
        return levels.get(level);
    }

    public void moveToNextStage() {
        this.currentStage++;
        if(this.currentStage >= MAX_STAGE) {
            this.currentStage = 1;
        }
    }

    private List<List<Chunk>> initializeLevels() {
        List<List<Chunk>> newLevels = new ArrayList<>();
        for (int i = 0; i <= NUM_LEVELS; i++) {
            newLevels.add(new ArrayList<>());
        }
        return newLevels;
    }

    @Override
    public String toString() {
        return "LeitnerBox{" + name + "}";
    }
}

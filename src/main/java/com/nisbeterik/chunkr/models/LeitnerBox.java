package com.nisbeterik.chunkr.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.nisbeterik.chunkr.models.Levels;

public class LeitnerBox {

    private List<List<Chunk>> levels;

    private final UUID BOX_ID;

    private String name;

    private final int NUM_LEVELS = 7;

    public LeitnerBox(String name) {
        this.BOX_ID = UUID.randomUUID();
        for(int i = 0; i<=NUM_LEVELS; i++) {
            levels.add(new ArrayList<>());
        }
        this.name = name;
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
}

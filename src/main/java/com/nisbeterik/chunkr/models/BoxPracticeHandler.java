package com.nisbeterik.chunkr.models;


import java.util.ArrayList;
import java.util.List;

public class BoxPracticeHandler {

    private LeitnerBox boxToPractice;

    private final String name;

    private final List<Integer> levelsToPractice;

    private final int numOfLevels;

    private int levelCounter = 0;

    private int chunkCounter = 0;

    private List<Chunk> chunksInLevel;

    private Chunk currentChunk;

    private boolean practiceOver = false;





    public BoxPracticeHandler(LeitnerBox box) {
        if (box == null) {
            throw new IllegalArgumentException("LeitnerBox cannot be null");
        }
        this.boxToPractice = box;
        this.name = boxToPractice.getName();
        this.levelsToPractice = StageLevelMapping.getTargetLevelsForStage(boxToPractice.getCurrentStage());
        this.numOfLevels = levelsToPractice.size();
        initializeChunksAndCurrentChunk();
    }

    public void knowChunk() {
        boxToPractice.moveToNextLevel(getCurrentChunk());
        setNextChunk();
    }

    public void dontKnowChunk() {
        boxToPractice.resetLevel(getCurrentChunk());
        setNextChunk();
    }

    public void setNextChunk() {
        updateChunkCounter();
        if(!practiceOver) {
            setCurrentChunk(getChunksInLevel().get(chunkCounter));
        }
    }

    private void updateLevelCounter() {
        this.levelCounter++;
        if(levelCounter >= numOfLevels) {
            boxToPractice.moveToNextStage();
            this.practiceOver = true;
        } else {
            setChunksInLevel(levelsToPractice.get(this.levelCounter));
            if(chunksInLevel.isEmpty()) {
                updateLevelCounter();
            } else {
                setCurrentChunk(getChunksInLevel().get(chunkCounter));
            }


        }
    }

    private void updateChunkCounter() {
        this.chunkCounter++;
        if(this.chunkCounter >= chunksInLevel.size()) {
            this.chunkCounter = 0;
            updateLevelCounter();
        }
    }

    private void setChunksInLevel(int level) {
        this.chunksInLevel = boxToPractice.getLevelList(level);
    }

    public String getName() {
        return name;
    }

    public int getCurrentLevel() {
        return levelsToPractice.get(levelCounter);
    }

    public List<Integer> getLevelsToPractice() {
        return levelsToPractice;
    }

    public int getNumOfLevels() {
        return numOfLevels;
    }

    public int getLevelCounter() {
        return levelCounter;
    }

    public int getChunkCounter() {
        return chunkCounter;
    }

    public LeitnerBox getBoxToPractice() {
        return boxToPractice;
    }

    public boolean isPracticeOver() {
        return practiceOver;
    }

    public List<Chunk> getChunksInLevel() {
        return chunksInLevel;
    }

    public Chunk getCurrentChunk() {
        return currentChunk;
    }

    private void setCurrentChunk(Chunk chunk) {
        this.currentChunk = chunk;
    }

    // checks if levelToPractice is an empty list, then checks the next level to practice
    private void initializeChunksAndCurrentChunk() {
        while (levelCounter < numOfLevels) {
            int currentLevel = levelsToPractice.get(levelCounter);
            this.chunksInLevel = boxToPractice.getLevelList(currentLevel);

            if (chunksInLevel != null && !chunksInLevel.isEmpty()) {
                this.currentChunk = chunksInLevel.get(0);
                return;
            } else {

                levelCounter++;
            }
        }

        // if all levels to practice are empty
        this.chunksInLevel = new ArrayList<>();
        this.currentChunk = null;
        this.practiceOver = true;
        System.out.println("No chunks to practice");
    }


}

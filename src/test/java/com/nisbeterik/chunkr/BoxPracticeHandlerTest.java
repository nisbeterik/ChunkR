package com.nisbeterik.chunkr;

import com.nisbeterik.chunkr.controller.PracticeBoxController;
import com.nisbeterik.chunkr.models.BoxPracticeHandler;
import com.nisbeterik.chunkr.models.Chunk;
import com.nisbeterik.chunkr.models.LeitnerBox;
import com.nisbeterik.chunkr.repository.LeitnerBoxRepository;
import com.nisbeterik.chunkr.repository.Repositories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BoxPracticeHandlerTest {

    private LeitnerBox box;

    private LeitnerBox box2;

    private Chunk chunk1;

    private Chunk chunk2;

    private BoxPracticeHandler bp;

    private BoxPracticeHandler bp2;



    @BeforeEach
    void setup() {
        box = new LeitnerBox("Test");
        chunk1 = new Chunk("term1", "answer1");
        chunk2 = new Chunk("term2", "answer2");
        box.addChunk(chunk1); box.addChunk(chunk2);
        box.moveToNextLevel(chunk2);
        bp = new BoxPracticeHandler(box);

        // box has empty level 2 list
        box2 = new LeitnerBox("Test2");
        box2.addChunk(chunk1); box2.addChunk(chunk2);
        bp2 = new BoxPracticeHandler(box2);
    }

    /***
     * Constructor tests
     */

    @Test
    void testNullBox() {
        LeitnerBox nullBox = null;
        assertThrows(IllegalArgumentException.class, () -> new BoxPracticeHandler(nullBox));
    }
    @Test
    void testLevelCounter() {
        assertEquals(0, bp.getLevelCounter());
    }


    @Test
    void testChunkCounter() {
        assertEquals(0, bp.getChunkCounter());
    }

    @Test
    void testBoxToPractice() {
        assertEquals(box, bp.getBoxToPractice());
    }

    @Test
    void testName() {
        assertEquals(bp.getName(), box.getName());
    }

    @Test
    void testLevelsToPractice() {
        assertEquals(1, box.getCurrentStage());
        List<Integer> l = new ArrayList<>(Arrays.asList(1, 0)); // the levels to practice for stage 1
        assertEquals(bp.getLevelsToPractice(), l);
    }

    @Test
    void testNumOfLevels() {
        assertEquals(2, bp.getNumOfLevels());
    }

    @Test
    void testChunksInLevels_normal() {
        assertEquals(bp.getChunksInLevel(), box.getLevelList(1));
    }

    @Test
    void testChunksInLevels_emptyLevelList() {
        assertEquals(bp.getChunksInLevel(), box.getLevelList(1));
    }

    @Test
    void testCurrentChunk() {
        assertEquals(chunk2, bp.getCurrentChunk());
    }

    /***
     * Method tests
     */




}

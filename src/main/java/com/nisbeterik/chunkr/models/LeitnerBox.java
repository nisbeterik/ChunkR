package com.nisbeterik.chunkr.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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


}

package com.nisbeterik.chunkr.repository;

public final class Repositories {

    private static final LeitnerBoxRepository LEITNER_BOX_REPOSITORY = new LeitnerBoxRepository();

    private Repositories() {
    }

    public static LeitnerBoxRepository getLeitnerBoxRepository() {return LEITNER_BOX_REPOSITORY;}
}

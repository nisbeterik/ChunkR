package com.nisbeterik.chunkr.models;

public class Chunk {

    private String term;

    private String answer;

    private int level;

    public Chunk(String term, String answer) {
        this.term = term;
        this.answer = answer;
        this.level = 1;
    }

    @Override
    public String toString() {
        return "Chunk{" +
                "term='" + term + '\'' +
                ", answer='" + answer + '\'' +
                ", level=" + level +'}'
                ;
    }
}

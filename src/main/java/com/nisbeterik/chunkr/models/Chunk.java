package com.nisbeterik.chunkr.models;

public class Chunk {

    private String term;

    private String answer;

    public Chunk(String term, String answer) {
        this.term = term;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Chunk{" +
                "term='" + term + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}

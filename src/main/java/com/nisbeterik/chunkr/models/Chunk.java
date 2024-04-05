package com.nisbeterik.chunkr.models;

public class Chunk {

    private String term;

    private String answer;

    private int level;

    public Chunk(String term, String answer) {
        this.term = term;
        this.answer = answer;
        this.level = 0; // 0 = 1 since Arrays have 0-index
    }

    public String getTerm() {
        return term;
    }

    public String getAnswer() {
        return answer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

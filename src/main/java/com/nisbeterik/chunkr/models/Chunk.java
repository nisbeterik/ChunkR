package com.nisbeterik.chunkr.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Chunk {

    private String term;

    private String answer;

    private int level;

    public Chunk(String term, String answer) {
        this.term = term;
        this.answer = answer;
        this.level = 0; // 0 = 1 since Arrays have 0-index
    }

    @JsonCreator
    public Chunk(@JsonProperty("term") String term,
                 @JsonProperty("answer") String answer,
                 @JsonProperty("level") int level) {
        this.term = term;
        this.answer = answer;
        this.level = level; // level set form JSON
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

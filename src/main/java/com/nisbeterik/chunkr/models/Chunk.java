package com.nisbeterik.chunkr.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chunk chunk = (Chunk) o;
        return Objects.equals(term, chunk.term) &&
                Objects.equals(answer, chunk.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(term, answer);
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

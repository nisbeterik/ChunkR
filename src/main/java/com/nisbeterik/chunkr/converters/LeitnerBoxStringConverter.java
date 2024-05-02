package com.nisbeterik.chunkr.converters;

import com.nisbeterik.chunkr.models.LeitnerBox;
import javafx.util.StringConverter;

public class LeitnerBoxStringConverter extends StringConverter<LeitnerBox> {


    @Override
    public String toString(LeitnerBox leitnerBox) {
        if (leitnerBox != null) {
            return leitnerBox.getName();
        }
        return null;
    }

    @Override
    public LeitnerBox fromString(String s) {
        return null;
    }
}

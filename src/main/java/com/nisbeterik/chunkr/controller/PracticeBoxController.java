package com.nisbeterik.chunkr.controller;

import com.nisbeterik.chunkr.models.BoxPracticeHandler;
import com.nisbeterik.chunkr.models.Chunk;
import com.nisbeterik.chunkr.models.LeitnerBox;
import com.nisbeterik.chunkr.models.StageLevelMapping;
import com.nisbeterik.chunkr.repository.LeitnerBoxRepository;
import com.nisbeterik.chunkr.repository.Repositories;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;
import java.util.Optional;

public class PracticeBoxController extends ParentController {
    public Label boxName;
    public Label currentLevel;
    @FXML
    private Label termLabel;

    private String term;
    private boolean termFlipped;
    LeitnerBoxRepository leitnerBoxRepository = Repositories.getLeitnerBoxRepository();




    @FXML
    public void initialize() {





    }

    private void updateTermLabel() {

    }

    @FXML
    private void flipTerm() {
        BoxPracticeHandler handler = new BoxPracticeHandler(leitnerBoxRepository.getLeitnerBoxByName(practiceBox));
        System.out.println(handler.getCurrentChunk());
        System.out.println(handler.getBoxToPractice());
    }

    @FXML
    private void knowTerm() {

    }

    @FXML
    private void dontKnowTerm() {

    }
}

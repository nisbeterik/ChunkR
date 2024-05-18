package com.nisbeterik.chunkr.controller;

import com.nisbeterik.chunkr.models.BoxPracticeHandler;
import com.nisbeterik.chunkr.repository.LeitnerBoxRepository;
import com.nisbeterik.chunkr.repository.Repositories;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


public class PracticeBoxController extends ParentController {
    public Label boxName;
    public Label currentLevel;
    public Button knowButton;
    public Button dontKnowButton;
    public Button practiceOverButton;
    public Button flipButton;
    public Label doYouKnowLabel;
    @FXML
    private Label termLabel;

    private String term;
    private boolean termFlipped;
    LeitnerBoxRepository leitnerBoxRepository = Repositories.getLeitnerBoxRepository();
    BoxPracticeHandler handler;



    @FXML
    public void initialize() {
        handler = new BoxPracticeHandler(leitnerBoxRepository.getLeitnerBoxByName(practiceBox));
        termLabel.setWrapText(true);
        termLabel.setText(handler.getCurrentChunk().getTerm());
        termFlipped = false;
        boxName.setText(handler.getName());
        currentLevel.setText(String.valueOf(handler.getCurrentLevel()));
        practiceOverButton.setVisible(false);

    }

    private void updateTermLabel() {
        termFlipped = false;
        termLabel.setText(handler.getCurrentChunk().getTerm());
        currentLevel.setText(String.valueOf(handler.getCurrentLevel()));
    }

    @FXML
    private void flipTerm() {
        if(!termFlipped) {
            termLabel.setText(handler.getCurrentChunk().getAnswer());
            termFlipped = true;
        }
        else {
            termLabel.setText(handler.getCurrentChunk().getTerm());
            termFlipped = false;
        }

    }

    @FXML
    private void knowTerm() {
        handler.knowChunk();
        if(!handler.isPracticeOver()) {
            updateTermLabel();
        }
        else {
            practiceOver();
        }

    }

    @FXML
    private void dontKnowTerm() {
        handler.dontKnowChunk();
    }

    @FXML
    private void endPractice(MouseEvent event) {
        returnToMyBoxes(event);
    }

    private void practiceOver() {
        dontKnowButton.setVisible(false);
        knowButton.setVisible(false);
        termLabel.setText("Practice is over");
        doYouKnowLabel.setVisible(false);
        flipButton.setVisible(false);
        practiceOverButton.setVisible(true);
    }

    @FXML
    private void returnToMyBoxes(MouseEvent mouseEvent) {
        redirect(mouseEvent, "my-boxes");
    }
}

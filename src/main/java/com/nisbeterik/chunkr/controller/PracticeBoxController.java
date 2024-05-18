package com.nisbeterik.chunkr.controller;

import com.nisbeterik.chunkr.models.BoxPracticeHandler;
import com.nisbeterik.chunkr.repository.LeitnerBoxRepository;
import com.nisbeterik.chunkr.repository.Repositories;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.ByteArrayInputStream;
import java.util.Base64;


public class PracticeBoxController extends ParentController {
    public Label boxName;
    public Label currentLevel;
    public Button knowButton;
    public Button dontKnowButton;
    public Button practiceOverButton;
    public Button flipButton;
    public Label doYouKnowLabel;
    public Button endPracticeButton;
    public ImageView chunkImageView;
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
        currentLevel.setText("Level: " + handler.getCurrentLevel());
        practiceOverButton.setVisible(false);


    }

    private void updateTermLabel() {
        if(!handler.isPracticeOver()) {
            termFlipped = false;
            termLabel.setText(handler.getCurrentChunk().getTerm());
            currentLevel.setText(String.valueOf(handler.getCurrentLevel()));
            loadImageFromBase64(handler.getCurrentChunk().getImageData());
        }
        else {
            practiceOver();
        }

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
        if(!handler.isPracticeOver()) {
            updateTermLabel();
        }
        else {
            practiceOver();
        }
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

    public void loadImageFromBase64(String base64ImageData) {
        if (base64ImageData != null && !base64ImageData.isEmpty()) {
            byte[] imageData = Base64.getDecoder().decode(base64ImageData);
            Image image = new Image(new ByteArrayInputStream(imageData));
            chunkImageView.setImage(image);
        } else {
            // Handle case where imageData is empty or null
            // You can set a placeholder image or display a message
            System.out.println("No image data available.");
        }
    }

    @FXML
    private void returnToMyBoxes(MouseEvent mouseEvent) {
        redirect(mouseEvent, "my-boxes");
    }
}

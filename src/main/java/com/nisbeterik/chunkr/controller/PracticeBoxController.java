package com.nisbeterik.chunkr.controller;

import com.nisbeterik.chunkr.models.BoxPracticeHandler;
import com.nisbeterik.chunkr.repository.LeitnerBoxRepository;
import com.nisbeterik.chunkr.repository.Repositories;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;


public class PracticeBoxController extends ParentController {


    // buttons

    public Button knowButton;
    public Button dontKnowButton;
    public Button practiceOverButton;
    public Button flipButton;
    public Button endPracticeButton;
    public Button showAnswerButton;
    public Button playAudioButton;

    // labels

    public Label boxName;
    public Label currentLevel;
    public Label doYouKnowLabel;

    public ImageView chunkImageView;
    public VBox vboxOne;
    @FXML
    private Text termText; // Changed from Label to Text
    @FXML
    private ScrollPane termScrollPane; // Added ScrollPane for text wrapping
    private MediaPlayer mediaPlayer;
    private boolean termFlipped;

    LeitnerBoxRepository leitnerBoxRepository = Repositories.getLeitnerBoxRepository();
    BoxPracticeHandler handler;




    @FXML
    public void initialize() {
        handler = new BoxPracticeHandler(leitnerBoxRepository.getLeitnerBoxByName(practiceBox));
        termText.setWrappingWidth(200);
        termScrollPane.setContent(new VBox(termText));
        if(!handler.isPracticeOver()) {
            initializeText();
            initializeButtonVisibility();
            loadImageFromBase64(handler.getCurrentChunk().getImageData());
            vboxOne.prefHeightProperty().bind(termScrollPane.heightProperty().add(20));
            termFlipped = false;
        } else
        {
            practiceOver();
        }


    }

    private void updateTermLabel() {
        if(!handler.isPracticeOver()) {
            if(termFlipped) {
                flipTerm();
            }
            termText.setText(handler.getCurrentChunk().getTerm()); // Change to setText for Text node
            currentLevel.setText(String.valueOf(handler.getCurrentLevel()));
            loadImageFromBase64(handler.getCurrentChunk().getImageData());
            chunkImageView.setVisible(false);
            flipButton.setVisible(true);

            if(handler.getCurrentChunk().getAudioData() == null) {
                playAudioButton.setVisible(false);
            } else
            {
                playAudioButton.setVisible(true);
            }
        }
        else {
            practiceOver();
        }
    }

    @FXML
    private void flipTerm() {
        if(!termFlipped) {
            termText.setVisible(false);
            termText.setText(handler.getCurrentChunk().getTerm());
            chunkImageView.setVisible(true);
            showAnswerButton.setVisible(true);
            termFlipped = true;
        }
        else {
            termText.setText(handler.getCurrentChunk().getTerm());
            termText.setVisible(true);
            chunkImageView.setVisible(false);
            showAnswerButton.setVisible(false);
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
            chunkImageView.setVisible(false);
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
        termText.setText("Practice is over");
        doYouKnowLabel.setVisible(false);
        flipButton.setVisible(false);
        practiceOverButton.setVisible(true);
        showAnswerButton.setVisible(false);
        playAudioButton.setVisible(false);
    }

    public void loadImageFromBase64(String base64ImageData) {
        if (base64ImageData != null && !base64ImageData.isEmpty()) {
            byte[] imageData = Base64.getDecoder().decode(base64ImageData);
            Image image = new Image(new ByteArrayInputStream(imageData));
            chunkImageView.setImage(image);
        } else {
            System.out.println("No image data available.");
        }
    }

    @FXML
    private void returnToMyBoxes(MouseEvent mouseEvent) {
        redirect(mouseEvent, "my-boxes");
    }

    @FXML
    private void showAnswer(MouseEvent mouseEvent) {
        showAnswerButton.setVisible(false);
        termText.setText(handler.getCurrentChunk().getTerm() + " = " + handler.getCurrentChunk().getAnswer());
        termText.setVisible(true);
        flipButton.setVisible(false);
    }

    @FXML
    public void playAudio(MouseEvent mouseEvent) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        String base64Audio = handler.getCurrentChunk().getAudioData();
        if (base64Audio != null && !base64Audio.isEmpty()) {
            try {
                byte[] audioData = Base64.getDecoder().decode(base64Audio);
                File tempAudioFile = File.createTempFile("audio", ".tmp");
                try (FileOutputStream fos = new FileOutputStream(tempAudioFile)) {
                    fos.write(audioData);
                }
                Media media = new Media(tempAudioFile.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error playing audio.");
            }
        } else {
            System.out.println("No audio data available.");
        }
    }

    private void initializeButtonVisibility() {
        practiceOverButton.setVisible(false);
        chunkImageView.setVisible(false);
        showAnswerButton.setVisible(false);
        if(handler.getCurrentChunk().getAudioData() == null) {
            playAudioButton.setVisible(false);
        }
    }

    private void initializeText() {
        termText.setText(handler.getCurrentChunk().getTerm());
        boxName.setText(handler.getName());
        currentLevel.setText("Level: " + handler.getCurrentLevel());
    }

}
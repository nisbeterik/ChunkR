package com.nisbeterik.chunkr.controller;


import com.nisbeterik.chunkr.models.Chunk;
import com.nisbeterik.chunkr.models.LeitnerBox;
import com.nisbeterik.chunkr.repository.LeitnerBoxRepository;
import com.nisbeterik.chunkr.repository.Repositories;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.lang.reflect.GenericDeclaration;


public class CreateLeitnerboxController extends ParentController {
        @FXML
        public Button createChunkButton;
        @FXML
        public TextField termField;
        @FXML
        public Label termLabel;
        @FXML
        public TextField defField;
        @FXML
        public Label defLabel;

    public Button homePageButton;
    private LeitnerBoxRepository leitnerBoxRepository = Repositories.getLeitnerBoxRepository();

        private LeitnerBox testBox;


        @FXML
        private Label createBoxLabel;

        @FXML
        private TextField createBoxField;

        @FXML
        private Button createBoxButton;


    @FXML
    public void initialize() {

    }

    @FXML
    void pressCreateBox(MouseEvent event) {

        testBox = leitnerBoxRepository.createLeitnerBox(createBoxField.getText());
        createBoxField.deleteText(0, createBoxField.getLength());
        System.out.println("Box created");

    }
    @FXML
    public void pressCreateChunk(MouseEvent mouseEvent) {
        testBox.addChunk(new Chunk(termField.getText(), defField.getText()));
        termField.deleteText(0, termField.getLength());
        defField.deleteText(0, defField.getLength());
        System.out.println("Chunk created");
    }


    public void pressHomePage(MouseEvent mouseEvent) throws IOException {
        redirect(mouseEvent, "home-page");
    }
}

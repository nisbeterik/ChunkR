package com.nisbeterik.chunkr.controller;


import com.nisbeterik.chunkr.repository.LeitnerBoxRepository;
import com.nisbeterik.chunkr.repository.Repositories;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.GenericDeclaration;


public class CreateLeitnerboxController {
        private LeitnerBoxRepository leitnerBoxRepository = Repositories.getLeitnerBoxRepository();


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

            leitnerBoxRepository.createLeitnerBox(createBoxField.getText());
            createBoxField.deleteText(0, createBoxField.getLength());

        }
}

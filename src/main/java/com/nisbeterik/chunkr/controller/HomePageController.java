package com.nisbeterik.chunkr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HomePageController extends ParentController{


    public Label headerLabel;
    public Button createBoxButton;
    public Button myBoxesButton;

    @FXML
    void initialize() {
        headerLabel.setText("Home Page");
        createBoxButton.setText("Create Leitner Box");
        myBoxesButton.setText("My Boxes");
    }

    public void pressCreateBoxButton(MouseEvent mouseEvent) {

    }

    public void pressMyBoxesButton(MouseEvent mouseEvent) {
    }
}

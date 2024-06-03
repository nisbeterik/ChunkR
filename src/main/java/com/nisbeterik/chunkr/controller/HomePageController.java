package com.nisbeterik.chunkr.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class HomePageController extends ParentController{


    public Label headerLabel;
    public Button createBoxButton;
    public Button myBoxesButton;
    public Button createChunkButton;

    @FXML
    void initialize() {
    }

    public void pressCreateBoxButton(MouseEvent mouseEvent) { redirect(mouseEvent, "create-leitnerbox.fxml"); }

    public void pressMyBoxesButton(MouseEvent mouseEvent) {
        redirect(mouseEvent, "practice-boxes");
    }

    public void pressCreateChunkButton(MouseEvent mouseEvent)  { redirect(mouseEvent, "create-chunk.fxml"); }
}

package com.nisbeterik.chunkr.controller;

import com.nisbeterik.chunkr.models.LeitnerBox;
import com.nisbeterik.chunkr.repository.LeitnerBoxRepository;
import com.nisbeterik.chunkr.repository.Repositories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class MyBoxesController {
    private LeitnerBoxRepository leitnerBoxRepository = Repositories.getLeitnerBoxRepository();
    @FXML
    public ListView<LeitnerBox> leitnerboxListView;
    @FXML
    public Label myBoxesLabel;
    @FXML
    public Button backToHomePageButton;

    @FXML
    void initialize() {
        myBoxesLabel.setText("My Boxes");
        backToHomePageButton.setText("Back to Home Page");

        // Convert the Set<LeitnerBox> to an ObservableList<LeitnerBox>
        ObservableSet<LeitnerBox> boxSet = FXCollections.observableSet(leitnerBoxRepository.getAll());
        ObservableList<LeitnerBox> boxes = FXCollections.observableArrayList(boxSet);

        // Set the cell factory
        leitnerboxListView.setCellFactory(lv -> new ListCell<LeitnerBox>() {
            @Override
            protected void updateItem(LeitnerBox box, boolean empty) {
                super.updateItem(box, empty);
                if (empty || box == null) {
                    setText(null);
                } else {
                    setText(box.getName());
                }
            }
        });

        leitnerboxListView.setItems(boxes);
    }

    public void pressHomePageButton(MouseEvent mouseEvent) {
    }
}

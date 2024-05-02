package com.nisbeterik.chunkr.controller;

import com.nisbeterik.chunkr.models.LeitnerBox;
import com.nisbeterik.chunkr.repository.LeitnerBoxRepository;
import com.nisbeterik.chunkr.repository.Repositories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class MyBoxesController extends ParentController {
    public Button practiceBoxButton;
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
        practiceBoxButton.setVisible(false);

        ObservableSet<LeitnerBox> boxSet = FXCollections.observableSet(leitnerBoxRepository.getAll());
        ObservableList<LeitnerBox> boxes = FXCollections.observableArrayList(boxSet);


        leitnerboxListView.setCellFactory(lv -> new ListCell<LeitnerBox>() {
            @Override
            protected void updateItem(LeitnerBox box, boolean empty) {
                super.updateItem(box, empty);
                if (empty || box == null) {
                    setText(null);
                } else {
                    setText(String.format("%s (Stage %d)", box.getName(), box.getCurrentStage()));
                }
            }
        });
        leitnerboxListView.setItems(boxes);
        leitnerboxListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            practiceBoxButton.setVisible(true);
        });
    }

    public void pressPracticeBox(MouseEvent mouseEvent) {
        LeitnerBox selectedBox = leitnerboxListView.getSelectionModel().getSelectedItem();
        setPracticeBox(selectedBox.getName());
        redirect(mouseEvent, "practice-box");
    }

    public void pressHomePageButton(MouseEvent mouseEvent) {
        redirect(mouseEvent, "home-page.fxml");
    }
}

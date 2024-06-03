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

public class ManageBoxesController extends ParentController {

    @FXML
    public Button manageChunksButton;
    @FXML
    public Button backToHomePageButton;
    @FXML
    public Button deleteBoxButton;

    private LeitnerBoxRepository leitnerBoxRepository = Repositories.getLeitnerBoxRepository();
    @FXML
    public ListView<LeitnerBox> leitnerboxListView;



    @FXML
    void initialize() {
        deleteBoxButton.setVisible(false);
        manageChunksButton.setVisible(false);
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
            deleteBoxButton.setVisible(true);
            manageChunksButton.setVisible(true);
        });

    }

    @FXML
    private void pressHomePageButton(MouseEvent mouseEvent) {
        redirect(mouseEvent, "home-page.fxml");
    }

    @FXML
    private void pressDeleteBoxButton(MouseEvent mouseEvent) {
        LeitnerBox selectedBox = leitnerboxListView.getSelectionModel().getSelectedItem();
        leitnerBoxRepository.removeByName(selectedBox.getName());
        initialize();
    }

    @FXML
    void pressManageChunks(MouseEvent mouseEvent) {
        LeitnerBox selectedBox = leitnerboxListView.getSelectionModel().getSelectedItem();
        setSelectedBox(selectedBox.getName());
        redirect(mouseEvent, "manage-chunks");
    }

}

package com.nisbeterik.chunkr.controller;

import com.nisbeterik.chunkr.models.Chunk;
import com.nisbeterik.chunkr.models.LeitnerBox;
import com.nisbeterik.chunkr.repository.LeitnerBoxRepository;
import com.nisbeterik.chunkr.repository.Repositories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class ManageChunksController extends ParentController {

    @FXML
    public ChoiceBox<String> levelsDropDown;
    public Button backButton;
    public ListView<Chunk> chunkList;
    public Button deleteChunkButton;

    private LeitnerBoxRepository leitnerBoxRepository = Repositories.getLeitnerBoxRepository();
    private LeitnerBox selectedBox;
    private ObservableList<Chunk> observableChunks;

    private static final List<String> LEVELS_LIST = List.of(
            "Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7"
    );

    @FXML
    void initialize() {
        selectedBox = leitnerBoxRepository.getLeitnerBoxByName(ParentController.selectedBox);
        initializeBox();
        addLevelSelectionListener();
        deleteChunkButton.setVisible(false);
        chunkList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Chunk chunk, boolean empty) {
                super.updateItem(chunk, empty);
                if (empty || chunk == null) {
                    setText(null);
                } else {
                    setText(chunk.getTerm());
                }
            }
        });
        chunkList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            deleteChunkButton.setVisible(true);
        });
    }

    private void initializeBox() {
        ObservableList<String> levels = FXCollections.observableArrayList(LEVELS_LIST);
        levelsDropDown.setItems(levels);
    }

    private void addLevelSelectionListener() {
        levelsDropDown.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.intValue() >= 0) {
                updateChunkListView(newValue.intValue());
            }
        });
    }

    private void updateChunkListView(int levelIndex) {
        if (selectedBox != null) {
            List<Chunk> chunksAtLevel = selectedBox.getLevelList(levelIndex);
            observableChunks = FXCollections.observableArrayList(chunksAtLevel);
            chunkList.setItems(observableChunks);
        }
    }

    public void backToManageBoxes(MouseEvent mouseEvent) {
        redirect(mouseEvent, "manage-boxes");
    }

    public void pressDeleteChunkButton(MouseEvent mouseEvent) {
        Chunk chunk = chunkList.getSelectionModel().getSelectedItem();
        if (chunk != null && selectedBox != null) {
            selectedBox.deleteChunk(chunk);
            observableChunks.remove(chunk);
            deleteChunkButton.setVisible(false);
        }
    }
}
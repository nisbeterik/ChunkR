package com.nisbeterik.chunkr.controller;

import com.nisbeterik.chunkr.converters.LeitnerBoxStringConverter;
import com.nisbeterik.chunkr.models.Chunk;
import com.nisbeterik.chunkr.models.LeitnerBox;
import com.nisbeterik.chunkr.repository.LeitnerBoxRepository;
import com.nisbeterik.chunkr.repository.Repositories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ChunkController extends ParentController {
    public ChoiceBox boxDropDown;
    public Label reqChoiceLabel;
    @FXML
    private Label termLabel;
    @FXML
    private Label definitionLabel;
    @FXML
    private TextField termField;
    @FXML
    private TextField definitionField;
    @FXML
    private Button createChunkButton;

    private LeitnerBoxRepository leitnerBoxRepository = Repositories.getLeitnerBoxRepository();

    Chunk chunk;




    @FXML
    public void initialize() {
        ObservableSet<LeitnerBox> boxSet = FXCollections.observableSet(leitnerBoxRepository.getAll());
        ObservableList<LeitnerBox> boxes = FXCollections.observableArrayList(boxSet);
        boxDropDown.setConverter(new LeitnerBoxStringConverter());
        boxDropDown.setItems(boxes);
    }

    public void pressCreateChunk(MouseEvent mouseEvent) {

        if(boxDropDown.getValue() != null) {
            LeitnerBox box = (LeitnerBox) boxDropDown.getValue();
            String term = termField.getText();
            String definition = definitionField.getText();
            chunk = new Chunk(term, definition);
            box.addChunk(chunk);
            System.out.println(chunk + "added to " + box.getName());
            reqChoiceLabel.setText("");
            termField.deleteText(0, termField.getCharacters().length());
            definitionField.deleteText(0, definitionField.getCharacters().length());

        }
        else {
            reqChoiceLabel.setText("Please select a box");
        }

    }
}
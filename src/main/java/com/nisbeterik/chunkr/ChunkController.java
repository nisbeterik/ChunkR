package com.nisbeterik.chunkr;

import com.nisbeterik.chunkr.models.Chunk;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ChunkController {
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

    Chunk chunk;




    @FXML
    public void initialize() {

    }

    public void createChunk(MouseEvent mouseEvent) {
        String term = termField.getText();
        String definition = definitionField.getText();
        chunk = new Chunk(term, definition);
        System.out.println(chunk);
    }
}
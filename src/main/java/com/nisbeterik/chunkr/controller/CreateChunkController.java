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
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class CreateChunkController extends ParentController {
    public ChoiceBox boxDropDown;
    public Label reqChoiceLabel;
    public Button backToHomePage;
    public StackPane canvasPane;
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
    @FXML
    private Canvas drawCanvas;

    private LeitnerBoxRepository leitnerBoxRepository = Repositories.getLeitnerBoxRepository();
    private GraphicsContext gc;

    Chunk chunk;

    @FXML
    public void initialize() {
        ObservableSet<LeitnerBox> boxSet = FXCollections.observableSet(leitnerBoxRepository.getAll());
        ObservableList<LeitnerBox> boxes = FXCollections.observableArrayList(boxSet);
        boxDropDown.setConverter(new LeitnerBoxStringConverter());
        boxDropDown.setItems(boxes);

        gc = drawCanvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setLineWidth(2);

        drawCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                gc.beginPath();
                gc.moveTo(e.getX(), e.getY());
                gc.stroke();
            }
        });

        drawCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                gc.lineTo(e.getX(), e.getY());
                gc.stroke();
            }
        });
    }

    public void pressCreateChunk(MouseEvent mouseEvent) {
        if(boxDropDown.getValue() != null) {
            LeitnerBox box = (LeitnerBox) boxDropDown.getValue();
            String term = termField.getText();
            String definition = definitionField.getText();
            String imageData = convertCanvasToImage(drawCanvas);
            chunk = new Chunk(term, definition, 0, imageData);
            box.addChunk(chunk);
            System.out.println(chunk + " added to " + box.getName());
            reqChoiceLabel.setText("");
            termField.clear();
            definitionField.clear();
            clearCanvas();
        } else {
            reqChoiceLabel.setText("Please select a box");
        }
    }

    private String convertCanvasToImage(Canvas canvas) {
        // Create a snapshot parameters instance to configure the snapshot
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT); // Optional: Set the background to transparent

        // Create a WritableImage from the canvas snapshot
        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(params, writableImage);

        // Convert the WritableImage to BufferedImage
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);

        // Convert BufferedImage to byte array and then to Base64
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", outputStream);
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void clearCanvas() {
        gc.clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
    }

    @FXML
    private void pressBackToMenu(MouseEvent event) {
        redirect(event, "home-page");
    }
}
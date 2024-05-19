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
import javafx.scene.control.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Stack;

public class CreateChunkController extends ParentController {
    public ChoiceBox boxDropDown;
    public Label reqChoiceLabel;
    public Button backToHomePage;
    public StackPane canvasPane;
    public Button clearDrawingButton;
    public Button undoButton;
    public ColorPicker colorPicker;
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

    @FXML private File selectedFile;

    @FXML
    private Button selectFileButton;

    private LeitnerBoxRepository leitnerBoxRepository = Repositories.getLeitnerBoxRepository();
    private GraphicsContext gc;
    private Stack<WritableImage> undoStack = new Stack<>();

    Chunk chunk;

    @FXML
    public void initialize() {
        initializeBox();
        initializeCanvas();
        selectFileButton.setOnAction(event -> openFileChooser());
    }

    public void pressCreateChunk(MouseEvent mouseEvent) {
        if(boxDropDown.getValue() != null) {
            if(!termField.getText().isEmpty() && !definitionField.getText().isEmpty()) {
                LeitnerBox box = (LeitnerBox) boxDropDown.getValue();
                String term = termField.getText();
                String definition = definitionField.getText();
                String imageData = convertCanvasToImage(drawCanvas);
                String audioData = "";
                if(selectedFile != null) {
                     audioData = convertAudioFileToBase64(selectedFile);
                }
                chunk = new Chunk(term, definition, 0, imageData, audioData);
                box.addChunk(chunk);
                System.out.println(chunk + " added to " + box.getName());
                reqChoiceLabel.setText("");
                termField.clear();
                definitionField.clear();
                clearCanvas();
            } else
            {
                reqChoiceLabel.setText("Term or definition can't be empty");
            }

        } else {
            reqChoiceLabel.setText("Please select a box");
        }
    }



    @FXML
    private void pressBackToMenu(MouseEvent event) {
        redirect(event, "home-page");
    }

    private void saveCanvasState() {
        WritableImage snapshot = new WritableImage((int) drawCanvas.getWidth(), (int) drawCanvas.getHeight());
        drawCanvas.snapshot(null, snapshot);
        undoStack.push(snapshot);
    }

    @FXML
    private void clearCanvas() {
        gc.clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
    }

    @FXML
    private void undoLastChange() {
        if (!undoStack.isEmpty()) {
            WritableImage previousState = undoStack.pop();
            gc.clearRect(0, 0, drawCanvas.getWidth(), drawCanvas.getHeight());
            gc.drawImage(previousState, 0, 0);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Undo");
            alert.setHeaderText(null);
            alert.setContentText("Nothing to undo.");
            alert.showAndWait();
        }
    }

    @FXML
    private void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files",
                        "*.mp3", "*.wav", "*.aac", "*.flac", "*.ogg", "*.m4a", "*.wma", "*.aiff", "*.aif", "*.alac", "*.amr")
        );
        Stage stage = (Stage) selectFileButton.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            if (isAudioFile(filePath)) {
                System.out.println("File selected: " + filePath);
                // Future code to handle the selected file will go here
            } else {
                System.out.println("Selected file is not a supported audio format.");
            }
        }
    }

    private boolean isAudioFile(String filePath) {
        String[] supportedExtensions = {".mp3", ".wav", ".aac", ".flac", ".ogg", ".m4a", ".wma", ".aiff", ".aif", ".alac", ".amr"};
        for (String extension : supportedExtensions) {
            if (filePath.toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    private String convertAudioFileToBase64(File audioFile) {
        try (FileInputStream fileInputStream = new FileInputStream(audioFile)) {
            byte[] fileBytes = new byte[(int) audioFile.length()];
            fileInputStream.read(fileBytes);
            return Base64.getEncoder().encodeToString(fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }



    private String convertCanvasToImage(Canvas canvas) {
        // create snapshot
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        // writeable image from snapshot
        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(params, writableImage);

        // convert to buffered image
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);

        // convert to byte array then base64 encode
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", outputStream);
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }





    private void initializeBox(){
        ObservableSet<LeitnerBox> boxSet = FXCollections.observableSet(leitnerBoxRepository.getAll());
        ObservableList<LeitnerBox> boxes = FXCollections.observableArrayList(boxSet);
        boxDropDown.setConverter(new LeitnerBoxStringConverter());
        boxDropDown.setItems(boxes);
    }

    private void initializeCanvas() {
        gc = drawCanvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.5);

        colorPicker.setOnAction(event -> {
            Color selectedColor = colorPicker.getValue();
            gc.setStroke(selectedColor);
        });

        drawCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                saveCanvasState();
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
}
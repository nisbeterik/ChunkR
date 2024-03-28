package com.nisbeterik.chunkr;


import com.nisbeterik.chunkr.Utils.FXMLUtil;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ChunkR extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final var hello = FXMLUtil.loadFxml("create-chunk");

        final var scene = new Scene((Parent) hello);
        stage.setScene(scene);
        stage.setResizable(false);
        
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

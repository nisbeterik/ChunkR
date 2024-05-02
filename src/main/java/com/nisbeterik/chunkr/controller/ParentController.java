package com.nisbeterik.chunkr.controller;

import com.nisbeterik.chunkr.Utils.FXMLUtil;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ParentController {



    protected static String practiceBox;

    public void setPracticeBox(String name) {
        practiceBox = name;
    }

    protected void redirect(Event event, String fxml) {
        Parent fxmlFile = FXMLUtil.loadFxml(fxml);
        Scene scene = new Scene(fxmlFile);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

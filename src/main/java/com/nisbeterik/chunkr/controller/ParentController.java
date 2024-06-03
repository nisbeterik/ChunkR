package com.nisbeterik.chunkr.controller;

import com.nisbeterik.chunkr.Utils.FXMLUtil;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ParentController {



    protected static String selectedBox;


    public void setSelectedBox(String name) {
        selectedBox = name;
    }

    protected void redirect(Event event, String fxml) {
        Parent fxmlFile = FXMLUtil.loadFxml(fxml);
        Scene scene = new Scene(fxmlFile);
        scene.getStylesheets().add(getClass().getResource("/css/colors.css").toExternalForm());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

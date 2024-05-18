module com.nisbeterik.ChunkR {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires java.desktop;
    requires javafx.swing;

    // Opens for FXML reflective access
    opens com.nisbeterik.chunkr to javafx.fxml;

    exports com.nisbeterik.chunkr;
    exports com.nisbeterik.chunkr.controller;
    opens com.nisbeterik.chunkr.models to com.fasterxml.jackson.databind;
    opens com.nisbeterik.chunkr.controller to javafx.fxml;
}
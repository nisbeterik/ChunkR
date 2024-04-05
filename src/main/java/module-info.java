module com.nisbeterik.ChunkR {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    // Opens for FXML reflective access
    opens com.nisbeterik.chunkr to javafx.fxml;

    exports com.nisbeterik.chunkr;
}
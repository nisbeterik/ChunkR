module com.nisbeterik.ChunkR {
    requires javafx.controls;
    requires javafx.fxml;

    // Opens for FXML reflective access
    opens com.nisbeterik.chunkr to javafx.fxml;

    exports com.nisbeterik.chunkr;
}
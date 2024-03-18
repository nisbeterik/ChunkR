package com.nisbeterik.chunkr.Utils;

import javafx.fxml.FXMLLoader;

import java.io.IOException;


import static java.util.Objects.requireNonNull;




/***
 * Utility class for loading fxml
 */
public final class FXMLUtil {

        private FXMLUtil() {
        }

        public static <T> T loadFxml(String fxmlName) {
            requireNonNull(fxmlName);

            fxmlName = fxmlName.endsWith(".fxml") ? fxmlName : fxmlName + ".fxml";

            try {
                return FXMLLoader.load(requireNonNull(FXMLUtil.class.getResource("/fxml/" + fxmlName)));
            } catch (IOException e) {
                throw new RuntimeException("Failed to load FXML file (" + fxmlName + ")");
            }
        }
    }


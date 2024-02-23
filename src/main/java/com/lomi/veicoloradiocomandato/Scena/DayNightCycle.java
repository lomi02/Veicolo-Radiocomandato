package com.lomi.veicoloradiocomandato.Scena;

import javafx.scene.shape.Rectangle;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import java.util.logging.Level;

public abstract class DayNightCycle {
    protected Rectangle rectangle;
    private static final Logger LOGGER = Logger.getLogger(DayNightCycle.class.getName());

    protected FXMLLoader loadFXML(String fxml) {
        URL url = DayNightCycle.class.getResource(fxml);
        if (url == null) {
            LOGGER.log(Level.SEVERE, "File not found: " + fxml);
            throw new RuntimeException("File not found: " + fxml);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load " + fxml, e);
            throw new RuntimeException("Failed to load " + fxml, e);
        }
        return fxmlLoader;
    }

    protected abstract void initializeDayNightCycle();

    public Rectangle getRectangle() {
        return rectangle;
    }
}
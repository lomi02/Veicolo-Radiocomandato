package com.lomi.veicoloradiocomandato.Scena;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameField {
    private final Scene scene;
    private static final Logger LOGGER = Logger.getLogger(GameField.class.getName());

    public GameField(Road road) {
        try {
            Background background = new Background();
            StackPane root = new StackPane();
            root.getChildren().addAll(background.getBackground(), road.getRoad());
            scene = new Scene(root, 600, 800);
        } catch (Exception e) {
            if (e instanceof NullPointerException)
                LOGGER.log(Level.SEVERE, "NullPointerException caught. This may be due to a null object returned when creating road/background", e);
            else
                LOGGER.log(Level.SEVERE, "An error occurred while creating the game field.", e);
            throw new RuntimeException("Failed to create the game field.", e);
        }
    }
    public Scene getScene() {
        return scene;
    }
}
package com.lomi.veicoloradiocomandato.Gioco;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.util.logging.Logger;
import java.util.logging.Level;

public class GameField {
    private final Scene scene;
    private static final Logger LOGGER = Logger.getLogger(GameField.class.getName());

    public GameField() {
        try {
            Background background = new Background();
            Road road = new Road();

            StackPane root = new StackPane();
            root.getChildren().addAll(background.getRectangle(), road.getRoad());
            scene = new Scene(root, 600, 800);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while creating the game field.", e);
            throw new RuntimeException("Failed to create the game field.", e);
        }
    }

    public Scene getScene() {
        return scene;
    }
}
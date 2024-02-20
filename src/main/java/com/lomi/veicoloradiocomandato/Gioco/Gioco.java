package com.lomi.veicoloradiocomandato.Gioco;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Gioco extends Application {
    private static final Logger LOGGER = Logger.getLogger(Gioco.class.getName());

    @Override
    public void start(Stage stage){
        try {
            GameManager gameManager = new GameManager();
            stage.setTitle("Veicolo Radiocomandato");
            stage.setScene(gameManager.getGameField().getScene());
            stage.show();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to start the game.", e);
            throw new RuntimeException("Failed to start the game.", e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
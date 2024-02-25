package com.lomi.veicoloradiocomandato.Gioco;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Gioco extends Application {
    private final GameManagerInterface gameManagerInterface;
    private static final Logger LOGGER = Logger.getLogger(Gioco.class.getName());

    public Gioco() {
        this.gameManagerInterface = new GameManager();
    }

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Veicolo Radiocomandato");
            gameManagerInterface.startGame(stage);
            stage.setScene(gameManagerInterface.getScene());
            stage.show();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to start the game.", e);
            throw new RuntimeException("Failed to start the game.", e);
        }
    }
}
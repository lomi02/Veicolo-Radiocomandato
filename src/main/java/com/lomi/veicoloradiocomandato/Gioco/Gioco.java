package com.lomi.veicoloradiocomandato.Gioco;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe Gioco estende Application e rappresenta la classe principale dell'applicazione.
 */
public class Gioco extends Application {

    private final GameManagerInterface gameManagerInterface;
    private static final Logger LOGGER = Logger.getLogger(Gioco.class.getName());

    /**
     * Costruttore della classe Gioco che inizializza il gestore di gioco.
     */
    public Gioco() {
        this.gameManagerInterface = new GameManager();
    }

    /**
     * Metodo principale chiamato al lancio dell'applicazione.
     *
     * @param stage Lo stage principale dell'applicazione.
     */
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
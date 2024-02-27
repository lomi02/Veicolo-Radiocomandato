package com.lomi.veicoloradiocomandato.Gioco;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe GameUI gestisce l'interfaccia utente del gioco.
 */
public class GameUI {

    private static final String UI_FXML_PATH = "/com/lomi/veicoloradiocomandato/ui.fxml";
    private static final Logger LOGGER = Logger.getLogger(GameUI.class.getName());
    private final GameManagerInterface gameManager;
    private int gameTimer = 0;
    private int score = 0;

    private AnimationTimer gameLoop;
    private AnimationTimer timerLoop;
    private long lastUpdate = 0;
    @FXML
    private VBox vbox;
    @FXML
    private Label timerLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label gearLabel;

    /**
     * Costruttore della classe GameUI.
     *
     * @param gameManager Il gestore del gioco associato all'interfaccia utente.
     */
    public GameUI(GameManagerInterface gameManager) {
        this.gameManager = gameManager;
        try {
            gameManager.loadFXML(UI_FXML_PATH, this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load " + UI_FXML_PATH, e);
        }
    }

    /**
     * Avvia il timer dell'interfaccia utente.
     */
    public void iniziaTimerUI() {
        this.gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                aggiornaUI();
            }
        };
        gameLoop.start();

        this.timerLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdate == 0 || now - lastUpdate >= 1_000_000_000) {
                    gameTimer++;
                    timerLabel.setText("Tempo: " + gameTimer + "s");
                    lastUpdate = now;
                }
            }
        };
        timerLoop.start();
    }

    /**
     * Aggiorna l'interfaccia utente con il punteggio e la marcia corrente.
     */
    private void aggiornaUI() {
        int currentGear = gameManager.getRadiocomando().getMarciaAttuale();
        score += 100 * currentGear;

        scoreLabel.setText("Punteggio: " + score);
        gearLabel.setText("Marcia: " + currentGear);
    }

    /**
     * Ferma il timer dell'interfaccia utente.
     */
    public void stopTimer() {
        if (this.gameLoop != null && this.timerLoop != null) {
            this.gameLoop.stop();
            this.timerLoop.stop();
        }
    }

    /**
     * Restituisce il layout dell'interfaccia utente.
     *
     * @return Il layout dell'interfaccia utente.
     */
    public VBox getUI() {
        return vbox;
    }
}
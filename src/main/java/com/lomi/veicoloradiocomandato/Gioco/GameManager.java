package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Radiocomando.Radiocomando;
import com.lomi.veicoloradiocomandato.Scena.GameField;
import com.lomi.veicoloradiocomandato.Scena.Road;
import javafx.animation.TranslateTransition;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameManager implements GameManagerInterface {
    private GameField gameField;
    private ObstacleManager obstacleManager;
    private Radiocomando radiocomando;
    private boolean gameRunning = true;
    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getName());

    public void setupGame(Stage stage, String chosenVehicle) {
        this.radiocomando = new Radiocomando();

        Road road = new Road(chosenVehicle, this);
        this.obstacleManager = road.getObstacleManager();

        this.startGame(road);
    }

    private void startGame(Road road) {
        try {
            this.gameField = new GameField(road);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize the game. Reason: " + e.getMessage(), e);
            throw new RuntimeException("Failed to initialize the game. Reason: " + e.getMessage(), e);
        }
    }

    public void restartGame() {
    }

    public void stopGame() {
        try {
            obstacleManager.getAnimations().forEach(TranslateTransition::stop);
            gameRunning = false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to stop game animations.", e);
        }
    }

    public GameField getGameField() {
        return gameField;
    }

    public Radiocomando getRadiocomando() {
        return radiocomando;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }
}
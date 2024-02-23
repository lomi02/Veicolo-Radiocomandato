package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Radiocomando.Radiocomando;
import com.lomi.veicoloradiocomandato.Scena.GameField;
import javafx.animation.TranslateTransition;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameManager {
    private static GameManager instance = null;
    private final String vehicle;
    private GameField gameField;
    private final ObstacleManager obstacleManager;
    private final Radiocomando radiocomando;
    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getName());

    private GameManager(String chosenVehicle, ObstacleManager obstacleManager) {
        this.vehicle = chosenVehicle;
        this.obstacleManager = obstacleManager;
        this.radiocomando = new Radiocomando();
        this.initGame();
    }

    public static GameManager getInstance(String chosenVehicle, ObstacleManager obstacleManager) {
        if (instance == null) {
            instance = new GameManager(chosenVehicle, obstacleManager);
        }
        return instance;
    }

    private void initGame() {
        try {
            this.gameField = new GameField(this.vehicle);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize the game.", e);
            throw new RuntimeException("Failed to initialize the game.", e);
        }
    }

    public void restartGame() {
        this.stopGame();
        this.initGame();
    }

    public void stopGame() {
        obstacleManager.getAnimations().forEach(TranslateTransition::stop);
    }

    public Radiocomando getRadiocomando() {
        return radiocomando;
    }

    public GameField getGameField() {
        return gameField;
    }
}
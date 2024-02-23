package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Radiocomando.Radiocomando;
import com.lomi.veicoloradiocomandato.Scena.GameField;
import com.lomi.veicoloradiocomandato.Scena.Road;
import javafx.animation.TranslateTransition;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameManager implements GameManagerInterface {
    private String vehicle;
    private GameField gameField;
    private ObstacleManager obstacleManager;
    private Radiocomando radiocomando;
    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getName());

    public GameManager() {}

    public void setupGame(String chosenVehicle) {
        this.vehicle = chosenVehicle;
        this.radiocomando = new Radiocomando();

        Road road = new Road(chosenVehicle, this);
        this.obstacleManager = road.getObstacleManager();

        this.startGame(road);
    }

    private void startGame(Road road) {
        try {
            this.gameField = new GameField(this, this.vehicle, road);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        obstacleManager.spawnObstacle(new Random());
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "Failed to spawn obstacle.", e);
                    }
                }
            }, 0, 3000);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to initialize the game.", e);
            throw new RuntimeException("Failed to initialize the game.", e);
        }
    }

    public void restartGame() {
        this.stopGame();
        this.setupGame(vehicle);
    }

    public void stopGame() {
        try {
            obstacleManager.getAnimations().forEach(TranslateTransition::stop);
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
}
package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Radiocomando.Marcia;
import com.lomi.veicoloradiocomandato.Radiocomando.Radiocomando;
import com.lomi.veicoloradiocomandato.Scena.GameField;
import com.lomi.veicoloradiocomandato.Scena.GameFieldInterface;
import com.lomi.veicoloradiocomandato.Scena.Road;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameManager implements GameManagerInterface {
    private Stage stage;
    private GameFieldInterface gameFieldInterface;
    private VeicoloManager veicoloManager;
    private ObstacleManager obstacleManager;
    private Radiocomando radiocomando;
    private CollisionManager collisionManager;
    private boolean gameRunning = true;
    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getName());

    public Scene getScene() {
        return this.gameFieldInterface.getScene();
    }

    public void startGame(Stage stage) {
        this.stage = stage;
        VehicleSelector vehicleSelector = new VehicleSelector();
        Optional<String> selectVehicle = vehicleSelector.selectVehicle();

        if (selectVehicle.isPresent()) {
            this.collisionManager = new CollisionManager();

            String chosenVehicle = selectVehicle.get();
            Road road = new Road(chosenVehicle, this);

            this.gameFieldInterface = new GameField(road);
            this.veicoloManager = gameFieldInterface.getVeicoloManager();
            this.obstacleManager = gameFieldInterface.getObstacleManager();

            Marcia marcia = new Marcia();
            this.radiocomando = new Radiocomando(gameFieldInterface, veicoloManager, marcia);
        }
    }

    public void restartGame() {
        obstacleManager.getAnimations().forEach(TranslateTransition::stop);

        ((Pane) gameFieldInterface.getScene().getRoot()).getChildren().clear();

        collisionManager.resetCollisions();
        gameRunning = true;

        startGame(stage);
        stage.setScene(gameFieldInterface.getScene());
    }

    public void stopGame() {
        try {
            obstacleManager.getAnimations().forEach(TranslateTransition::stop);
            obstacleManager.stopObstacleGeneration();
            veicoloManager.getAnimations().forEach(TranslateTransition::stop);
            gameRunning = false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to stop game animations.", e);
        }
    }

    public GameField getGameField() {
        return (GameField) gameFieldInterface;
    }

    public Radiocomando getRadiocomando() {
        return radiocomando;
    }

    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }
}
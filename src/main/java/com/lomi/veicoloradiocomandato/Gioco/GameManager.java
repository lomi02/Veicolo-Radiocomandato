package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Radiocomando.Radiocomando;
import com.lomi.veicoloradiocomandato.Radiocomando.RadiocomandoInterface;
import com.lomi.veicoloradiocomandato.Scena.GameField;
import com.lomi.veicoloradiocomandato.Scena.GameFieldInterface;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameManager implements GameManagerInterface {
    private Stage stage;
    private GameFieldInterface gameField;
    private RadiocomandoInterface radiocomando;
    private VeicoloManager veicoloManager;
    private ObstacleManager obstacleManager;
    private CollisionManager collisionManager;
    private GameUI gameUI;
    private boolean gameRunning = true;
    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getName());

    @Override
    public Scene getScene() {
        return this.gameField.getScene();
    }

    @Override
    public void startGame(Stage stage) {
        this.stage = stage;
        VehicleSelector vehicleSelector = new VehicleSelector();
        Optional<String> selectVehicle = vehicleSelector.selectVehicle();

        if (selectVehicle.isPresent()) {
            this.collisionManager = new CollisionManager();
            String chosenVehicle = selectVehicle.get();
            this.gameUI = new GameUI(this);
            this.gameField = new GameField(this, chosenVehicle, gameUI);
            this.veicoloManager = gameField.getVeicoloManager();
            this.radiocomando = new Radiocomando(this, veicoloManager);
            this.obstacleManager = gameField.getObstacleManager();

            obstacleManager.startObstacleGeneration();
        }
    }

    @Override
    public void restartGame() {
        stage.close();
        obstacleManager.getAnimations().forEach(TranslateTransition::stop);

        ((Pane) gameField.getScene().getRoot()).getChildren().clear();

        collisionManager.resetCollisions();
        gameRunning = true;

        startGame(stage);
        stage.setScene(gameField.getScene());
        stage.show();
    }

    @Override
    public void stopGame() {
        try {
            obstacleManager.getAnimations().forEach(TranslateTransition::stop);
            obstacleManager.stopObstacleGeneration();
            veicoloManager.getAnimations().forEach(TranslateTransition::stop);
            radiocomando.resetMarcia();
            gameUI.stopTimer();
            gameRunning = false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to stop game animations.", e);
        }
    }

    @Override
    public GameFieldInterface getGameField() {
        return gameField;
    }

    @Override
    public RadiocomandoInterface getRadiocomando() {
        return radiocomando;
    }

    @Override
    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    @Override
    public ObstacleManager getObstacleManager() {
        return obstacleManager;
    }

    @Override
    public VeicoloManager getVeicoloManager() {
        return veicoloManager;
    }

    @Override
    public boolean isGameRunning() {
        return gameRunning;
    }

    @Override
    public FXMLLoader loadFXML(String fxml, Object controller) throws IOException {
        URL url = getClass().getResource(fxml);
        if (url == null) {
            LOGGER.log(Level.SEVERE, "File not found: " + fxml);
            throw new RuntimeException("File not found: " + fxml);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        fxmlLoader.setController(controller);
        fxmlLoader.load();
        return fxmlLoader;
    }
}
package com.lomi.veicoloradiocomandato.Scena;

import com.lomi.veicoloradiocomandato.Gioco.GameUI;
import com.lomi.veicoloradiocomandato.Gioco.GameManagerInterface;
import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Vehicle.Veicolo;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class GameField implements GameFieldInterface {
    private final Scene scene;
    private final Road road;

    public GameField(GameManagerInterface gameManager, String chosenVehicle, GameUI gameUI) {
        Background background = new Background(gameManager);
        Road road = new Road(chosenVehicle, gameManager);
        this.road = road;

        gameUI.iniziaTimerUI();

        StackPane root = new StackPane();
        root.getChildren().addAll(background.getBackground(), road.getRoad(), gameUI.getUI());
        scene = new Scene(root, 600, 800);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lomi/veicoloradiocomandato/styles.css")).toExternalForm());    }

    @Override
    public Scene getScene() {
        return scene;
    }

    @Override
    public GridPane getRoad() {
        return road.getRoad();
    }

    @Override
    public Veicolo getVeicolo() {
        return road.getVeicolo();
    }

    @Override
    public VeicoloManager getVeicoloManager() {
        return road.getVeicoloManager();
    }

    @Override
    public ObstacleManager getObstacleManager() {
        return road.getObstacleManager();
    }
}
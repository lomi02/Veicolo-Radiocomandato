package com.lomi.veicoloradiocomandato.Scena;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Vehicle.Veicolo;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GameField implements GameFieldInterface {
    private final Scene scene;
    private final Road road;

    public GameField(Road road) {
        this.road = road;
        Background background = new Background();
        StackPane root = new StackPane();
        root.getChildren().addAll(background.getBackground(), road.getRoad());
        scene = new Scene(root, 600, 800);
    }

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
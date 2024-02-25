package com.lomi.veicoloradiocomandato.Scena;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Vehicle.Veicolo;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public interface GameFieldInterface {
    Scene getScene();

    GridPane getRoad();

    Veicolo getVeicolo();

    VeicoloManager getVeicoloManager();

    ObstacleManager getObstacleManager();
}
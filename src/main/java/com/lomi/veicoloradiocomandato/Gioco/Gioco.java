package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleFetcher;
import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Scena.Road;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gioco extends Application {
    private static final Logger LOGGER = Logger.getLogger(Gioco.class.getName());
    private final ObstacleFetcher obstacleFetcher = new ObstacleFetcher();

    @Override
    public void start(Stage stage) {
        try {
            Optional<String> result = Menu.showVehicleSelection();

            if (result.isPresent()) {

                String chosenVehicle = result.get();
                Road road = new Road(chosenVehicle);
                ObstacleManager obstacleManager = new ObstacleManager(road.getRoad(),
                        obstacleFetcher.getObstacles(),
                        road,
                        new VeicoloManager(road.getRoad()), null);
                GameManager gameManager = GameManager.getInstance(chosenVehicle, obstacleManager);
                obstacleManager.setGameManager(gameManager);
                stage.setTitle("Veicolo Radiocomandato");
                stage.setScene(gameManager.getGameField().getScene());

                stage.show();
            } else {
                LOGGER.log(Level.INFO, "L'utente non ha scelto un veicolo. Uscita dal gioco.");
                Platform.exit();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to start the game.", e);
            throw new RuntimeException("Failed to start the game.", e);
        }
    }
}
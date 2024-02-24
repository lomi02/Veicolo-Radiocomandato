package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Scena.Road;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gioco extends Application {
    private final GameManagerInterface gameManager;
    private static final Logger LOGGER = Logger.getLogger(Gioco.class.getName());

    public Gioco() {
        this.gameManager = new GameManager();
    }

    @Override
    public void start(Stage stage) {
        try {
            VehicleSelector vehicleSelector = new VehicleSelector();
            Optional<String> selectVehicle = vehicleSelector.selectVehicle();

            if (selectVehicle.isPresent()) {
                String chosenVehicle = selectVehicle.get();
                new Road(chosenVehicle, gameManager);

                gameManager.setupGame(chosenVehicle);

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
package com.lomi.veicoloradiocomandato.Gioco;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gioco extends Application {
    private static final Logger LOGGER = Logger.getLogger(Gioco.class.getName());

    @Override
    public void start(Stage stage) {
        try {
            Optional<String> result = Menu.showVehicleSelection();

            if (result.isPresent()) {

                String chosenVehicle = result.get();
                GameManager gameManager = new GameManager(chosenVehicle);

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
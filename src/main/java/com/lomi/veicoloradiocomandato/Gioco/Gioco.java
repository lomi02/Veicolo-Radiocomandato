package com.lomi.veicoloradiocomandato.Gioco;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gioco extends Application {
    // Logger utilizzato per il logging delle informazioni
    private static final Logger LOGGER = Logger.getLogger(Gioco.class.getName());

    @Override
    public void start(Stage stage) {
        try {

            // Mostra il menu di selezione del veicolo e ottiene il risultato
            Optional<String> result = Menu.showVehicleSelection();

            // Verifica se l'utente ha effettivamente fatto una scelta
            if (result.isPresent()) {

                // Ottiene la scelta dell'utente
                String chosenVehicle = result.get();

                // Crea il gestore di gioco come prima, ma passa il veicolo scelto come argomento
                GameManager gameManager = new GameManager(chosenVehicle);

                // Imposta il titolo della finestra di gioco e la scena iniziale
                stage.setTitle("Veicolo Radiocomandato");
                stage.setScene(gameManager.getGameField().getScene());

                // Mostra la finestra di gioco
                stage.show();

            } else {

                // Se l'utente non ha fatto una scelta, registra l'informazione e termina il gioco
                LOGGER.log(Level.INFO, "L'utente non ha scelto un veicolo. Uscita dal gioco.");
                Platform.exit();
            }
        } catch (Exception e) {

            // Se si verifica un'eccezione, la registra e la lancia come RuntimeException
            LOGGER.log(Level.SEVERE, "Failed to start the game.", e);
            throw new RuntimeException("Failed to start the game.", e);
        }
    }
}
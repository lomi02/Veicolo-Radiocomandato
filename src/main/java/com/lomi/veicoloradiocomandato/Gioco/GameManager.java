package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Radiocomando.Radiocomando;
import java.util.logging.Logger;
import java.util.logging.Level;

public class GameManager {
    private final Radiocomando radiocomando;
    private final GameField gameField;
    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getName());

    public GameManager(String chosenVehicle) {
        try {
            this.radiocomando = new Radiocomando();

            // Try-catch block around creation of GameField
            try {  // [AGGIUNTO]
                this.gameField = new GameField(chosenVehicle);
            } catch(Exception e){
                LOGGER.log(Level.SEVERE, "Failed to create GameField.", e);
                throw new RuntimeException("Failed to create GameField.", e);
            }  // [AGGIUNTO]

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to create GameManager.", e);
            throw new RuntimeException("Failed to create GameManager.", e);
        }
    }

    public Radiocomando getRadiocomando() {
        return radiocomando;
    }

    public GameField getGameField() {
        return gameField;
    }
}
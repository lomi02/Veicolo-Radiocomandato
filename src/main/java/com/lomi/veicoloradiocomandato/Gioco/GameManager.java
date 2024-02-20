package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Radiocomando.Radiocomando;
import java.util.logging.Logger;
import java.util.logging.Level;

public class GameManager {
    private final Radiocomando radiocomando;
    private final GameField gameField;
    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getName());

    public GameManager() {
        try {
            this.radiocomando = new Radiocomando();
            this.gameField = new GameField();
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
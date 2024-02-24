package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Radiocomando.Radiocomando;
import com.lomi.veicoloradiocomandato.Scena.GameField;
import javafx.stage.Stage;

public interface GameManagerInterface {
    void setupGame(Stage stage, String chosenVehicle);

    void stopGame();

    void restartGame();

    GameField getGameField();

    Radiocomando getRadiocomando();

    boolean isGameRunning();
}
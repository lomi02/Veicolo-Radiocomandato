package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Radiocomando.Radiocomando;
import com.lomi.veicoloradiocomandato.Scena.GameField;

public interface GameManagerInterface {
    void setupGame(String chosenVehicle);
    void stopGame();
    void restartGame();
    GameField getGameField();
    Radiocomando getRadiocomando();
}
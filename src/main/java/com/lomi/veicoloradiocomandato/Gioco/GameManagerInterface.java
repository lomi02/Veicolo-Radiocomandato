package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Radiocomando.Radiocomando;
import com.lomi.veicoloradiocomandato.Scena.GameField;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface GameManagerInterface {
    Scene getScene();

    void startGame(Stage stage);

    void stopGame();

    void restartGame();

    GameField getGameField();

    Radiocomando getRadiocomando();
    CollisionManager getCollisionManager();

    boolean isGameRunning();
}
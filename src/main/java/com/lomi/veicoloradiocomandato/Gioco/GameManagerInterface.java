package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Radiocomando.RadiocomandoInterface;
import com.lomi.veicoloradiocomandato.Scena.GameFieldInterface;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public interface GameManagerInterface {
    Scene getScene();

    void startGame(Stage stage);

    void stopGame();

    void restartGame();

    GameFieldInterface getGameField();

    RadiocomandoInterface getRadiocomando();

    CollisionManager getCollisionManager();

    ObstacleManager getObstacleManager();

    VeicoloManager getVeicoloManager();

    boolean isGameRunning();

    FXMLLoader loadFXML(String fxml, Object controller) throws IOException;
}
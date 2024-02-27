package com.lomi.veicoloradiocomandato.Radiocomando;

import com.lomi.veicoloradiocomandato.Gioco.GameManagerInterface;
import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Radiocomando implements RadiocomandoInterface {
    private int marciaAttuale = 3;
    private boolean isAnimating = false;

    public Radiocomando(GameManagerInterface gameManagerInterface, VeicoloManager veicoloManager) {
        marciaAttuale = getMarciaAttuale();
        EventHandler<KeyEvent> keyEventHandler = event -> {
            if (!veicoloManager.isAnimating()) {
                ObstacleManager obstacleManager = gameManagerInterface.getObstacleManager();
                isAnimating = true;
                switch (event.getCode()) {
                    case W:
                        obstacleManager.stopObstacleGeneration();
                        incrementaMarcia();
                        obstacleManager.startObstacleGeneration();
                        break;
                    case S:
                        obstacleManager.stopObstacleGeneration();
                        decrementaMarcia();
                        obstacleManager.startObstacleGeneration();
                        break;
                    case A:
                        veicoloManager.spostaVeicolo("sinistra");
                        break;
                    case D:
                        veicoloManager.spostaVeicolo("destra");
                        break;
                }
                isAnimating = false;
            }
        };
        gameManagerInterface.getGameField().getScene().setOnKeyPressed(keyEventHandler);
    }

    @Override
    public int getMarciaAttuale() {
        return marciaAttuale;
    }

    @Override
    public void resetMarcia() {
        marciaAttuale = 3;
    }

    @Override
    public void incrementaMarcia() {
        if (marciaAttuale < 5) {
            marciaAttuale++;
        }
    }

    @Override
    public void decrementaMarcia() {
        if (marciaAttuale > 1) {
            marciaAttuale--;
        }
    }
}
package com.lomi.veicoloradiocomandato.Radiocomando;

import com.lomi.veicoloradiocomandato.Gioco.GameManagerInterface;
import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * La classe Radiocomando implementa l'interfaccia RadiocomandoInterface
 * e gestisce le operazioni relative al radiocomando di un veicolo.
 */
public class Radiocomando implements RadiocomandoInterface {

    private final IncrementaMarciaCommand incrementaMarciaCommand;
    private final DecrementaMarciaCommand decrementaMarciaCommand;
    private final SpostaVeicoloSinistraCommand spostaVeicoloSinistraCommand;
    private final SpostaVeicoloDestraCommand spostaVeicoloDestraCommand;
    private int marciaAttuale = 3;

    /**
     * Costruttore della classe Radiocomando.
     *
     * @param gameManagerInterface L'interfaccia del gestore di gioco.
     */
    public Radiocomando(GameManagerInterface gameManagerInterface) {
        VeicoloManager veicoloManager = gameManagerInterface.getVeicoloManager();
        ObstacleManager obstacleManager = gameManagerInterface.getObstacleManager();
        incrementaMarciaCommand = new IncrementaMarciaCommand(this, obstacleManager);
        decrementaMarciaCommand = new DecrementaMarciaCommand(this, obstacleManager);
        spostaVeicoloSinistraCommand = new SpostaVeicoloSinistraCommand(veicoloManager);
        spostaVeicoloDestraCommand = new SpostaVeicoloDestraCommand(veicoloManager);

        EventHandler<KeyEvent> keyEventHandler = event -> {
            if (!veicoloManager.isAnimating()) {
                switch (event.getCode()) {
                    case W:
                        incrementaMarciaCommand.execute();
                        break;
                    case S:
                        decrementaMarciaCommand.execute();
                        break;
                    case A:
                        spostaVeicoloSinistraCommand.execute();
                        break;
                    case D:
                        spostaVeicoloDestraCommand.execute();
                        break;
                }
            }
        };
        gameManagerInterface.getGameField().getScene().setOnKeyPressed(keyEventHandler);
    }

    /**
     * Restituisce la marcia attuale impostata sul radiocomando.
     *
     * @return La marcia attuale.
     */
    @Override
    public int getMarciaAttuale() {
        return marciaAttuale;
    }

    /**
     * Reimposta la marcia sul radiocomando, riportandola alla marcia iniziale.
     */
    @Override
    public void resetMarcia() {
        marciaAttuale = 3;
    }

    /**
     * Incrementa la marcia attuale sul radiocomando, se possibile.
     */
    @Override
    public void incrementaMarcia() {
        if (marciaAttuale < 5) {
            marciaAttuale++;
        }
    }

    /**
     * Decrementa la marcia attuale sul radiocomando, se possibile.
     */
    @Override
    public void decrementaMarcia() {
        if (marciaAttuale > 1) {
            marciaAttuale--;
        }
    }
}
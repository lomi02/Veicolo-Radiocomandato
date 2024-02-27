package com.lomi.veicoloradiocomandato.Scena;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.scene.Scene;

/**
 * L'interfaccia GameFieldInterface definisce i metodi necessari per ottenere informazioni sul campo di gioco.
 */
public interface GameFieldInterface {

    /**
     * Restituisce l'istanza di Scene associata al campo di gioco.
     *
     * @return L'istanza di Scene rappresentante il campo di gioco.
     */
    Scene getScene();

    /**
     * Restituisce il gestore dei veicoli associato al campo di gioco.
     *
     * @return L'istanza di VeicoloManager.
     */
    VeicoloManager getVeicoloManager();

    /**
     * Restituisce il gestore degli ostacoli associato al campo di gioco.
     *
     * @return L'istanza di ObstacleManager.
     */
    ObstacleManager getObstacleManager();
}
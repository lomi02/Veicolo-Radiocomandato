package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Radiocomando.RadiocomandoInterface;
import com.lomi.veicoloradiocomandato.Scena.GameFieldInterface;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * L'interfaccia GameManagerInterface definisce il contratto per la gestione del gioco.
 */
public interface GameManagerInterface {

    /**
     * Restituisce la scena associata al gioco.
     *
     * @return La scena del gioco.
     */
    Scene getScene();

    /**
     * Avvia il gioco sulla finestra specificata.
     *
     * @param stage Lo stage su cui avviare il gioco.
     */
    void startGame(Stage stage);

    /**
     * Ferma il gioco in esecuzione.
     */
    void stopGame();

    /**
     * Riavvia il gioco.
     */
    void restartGame();

    /**
     * Restituisce l'interfaccia del campo di gioco.
     *
     * @return L'interfaccia del campo di gioco.
     */
    GameFieldInterface getGameField();

    /**
     * Restituisce l'interfaccia del radiocomando.
     *
     * @return L'interfaccia del radiocomando.
     */
    RadiocomandoInterface getRadiocomando();

    /**
     * Restituisce il gestore delle collisioni del gioco.
     *
     * @return Il gestore delle collisioni.
     */
    CollisionManager getCollisionManager();

    /**
     * Restituisce il gestore dei veicoli del gioco.
     *
     * @return Il gestore dei veicoli.
     */
    VeicoloManager getVeicoloManager();

    /**
     * Restituisce il gestore degli ostacoli del gioco.
     *
     * @return Il gestore degli ostacoli.
     */
    ObstacleManager getObstacleManager();

    /**
     * Verifica se il gioco e' in esecuzione.
     *
     * @return true se il gioco è in esecuzione, false altrimenti.
     */
    boolean isGameRunning();

    /**
     * Carica un file FXML associato a un controller specifico.
     *
     * @param fxml       Il percorso del file FXML.
     * @param controller Il controller associato al file FXML.
     * @throws IOException Se si verifica un errore durante il caricamento del file FXML.
     */
    void loadFXML(String fxml, Object controller) throws IOException;
}
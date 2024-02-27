package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Radiocomando.Radiocomando;
import com.lomi.veicoloradiocomandato.Radiocomando.RadiocomandoInterface;
import com.lomi.veicoloradiocomandato.Scena.GameField;
import com.lomi.veicoloradiocomandato.Scena.GameFieldInterface;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe GameManager implementa l'interfaccia GameManagerInterface e gestisce il flusso del gioco.
 */
public class GameManager implements GameManagerInterface {

    private Stage stage;
    private GameFieldInterface gameField;
    private RadiocomandoInterface radiocomando;
    private VeicoloManager veicoloManager;
    private ObstacleManager obstacleManager;
    private CollisionManager collisionManager;
    private GameUI gameUI;
    private boolean gameRunning = true;
    private static final Logger LOGGER = Logger.getLogger(GameManager.class.getName());

    /**
     * Restituisce la scena associata al gioco.
     *
     * @return La scena del gioco.
     */
    @Override
    public Scene getScene() {
        return this.gameField.getScene();
    }

    /**
     * Avvia il gioco sulla finestra specificata.
     *
     * @param stage Lo stage su cui avviare il gioco.
     */
    @Override
    public void startGame(Stage stage) {
        this.stage = stage;
        VehicleSelector vehicleSelector = new VehicleSelector();
        Optional<String> selectVehicle = vehicleSelector.selectVehicle();

        if (selectVehicle.isPresent()) {
            this.collisionManager = new CollisionManager();
            String chosenVehicle = selectVehicle.get();
            this.gameUI = new GameUI(this);
            this.gameField = new GameField(this, chosenVehicle, gameUI);
            this.veicoloManager = gameField.getVeicoloManager();
            this.obstacleManager = gameField.getObstacleManager();
            this.radiocomando = new Radiocomando(this);

            obstacleManager.startObstacleGeneration();
        }
    }

    /**
     * Riavvia il gioco, chiudendo la finestra corrente e avviandone una nuova.
     */
    @Override
    public void restartGame() {
        stage.close();
        obstacleManager.getAnimations().forEach(TranslateTransition::stop);

        ((Pane) gameField.getScene().getRoot()).getChildren().clear();

        collisionManager.resetCollisions();
        gameRunning = true;

        startGame(stage);
        stage.setScene(gameField.getScene());
        stage.show();
    }

    /**
     * Ferma il gioco, arrestando tutte le animazioni e reimpostando lo stato iniziale.
     */
    @Override
    public void stopGame() {
        try {
            obstacleManager.getAnimations().forEach(TranslateTransition::stop);
            obstacleManager.stopObstacleGeneration();
            veicoloManager.getAnimations().forEach(TranslateTransition::stop);
            radiocomando.resetMarcia();
            gameUI.stopTimer();
            gameRunning = false;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to stop game animations.", e);
        }
    }

    /**
     * Restituisce l'interfaccia del campo di gioco.
     *
     * @return L'interfaccia del campo di gioco.
     */
    @Override
    public GameFieldInterface getGameField() {
        return gameField;
    }

    /**
     * Restituisce l'interfaccia del radiocomando.
     *
     * @return L'interfaccia del radiocomando.
     */
    @Override
    public RadiocomandoInterface getRadiocomando() {
        return radiocomando;
    }

    /**
     * Restituisce il gestore delle collisioni del gioco.
     *
     * @return Il gestore delle collisioni.
     */
    @Override
    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    /**
     * Restituisce il gestore dei veicoli del gioco.
     *
     * @return Il gestore dei veicoli.
     */
    @Override
    public VeicoloManager getVeicoloManager() {
        return veicoloManager;
    }

    /**
     * Restituisce il gestore degli ostacoli del gioco.
     *
     * @return Il gestore degli ostacoli.
     */
    @Override
    public ObstacleManager getObstacleManager() {
        return obstacleManager;
    }

    /**
     * Verifica se il gioco è in esecuzione.
     *
     * @return true se il gioco è in esecuzione, false altrimenti.
     */
    @Override
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Carica un file FXML associato a un controller specifico.
     *
     * @param fxml      Il percorso del file FXML.
     * @param controller Il controller associato al file FXML.
     * @throws IOException Se si verifica un errore durante il caricamento del file FXML.
     */
    @Override
    public void loadFXML(String fxml, Object controller) throws IOException {
        URL url = getClass().getResource(fxml);
        if (url == null) {
            LOGGER.log(Level.SEVERE, "File not found: " + fxml);
            throw new RuntimeException("File not found: " + fxml);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        fxmlLoader.setController(controller);
        fxmlLoader.load();
    }
}
package com.lomi.veicoloradiocomandato.Scena;

import com.lomi.veicoloradiocomandato.Gioco.GameManagerInterface;
import com.lomi.veicoloradiocomandato.Gioco.GameUI;
import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import java.util.Objects;

/**
 * La classe GameField implementa l'interfaccia GameFieldInterface e rappresenta il campo di gioco.
 */
public class GameField implements GameFieldInterface {

    private final Scene scene;
    private final Road road;

    /**
     * Costruttore della classe GameField.
     *
     * @param gameManager   L'interfaccia del gestore di gioco.
     * @param chosenVehicle Il veicolo scelto dal giocatore.
     * @param gameUI        L'interfaccia utente di gioco.
     */
    public GameField(GameManagerInterface gameManager, String chosenVehicle, GameUI gameUI) {
        Background background = new Background(gameManager);
        Road road = new Road(chosenVehicle, gameManager);
        this.road = road;

        gameUI.iniziaTimerUI();

        StackPane root = new StackPane();
        root.getChildren().addAll(background.getBackground(), road.getRoad(), gameUI.getUI());
        scene = new Scene(root, 600, 800);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/lomi/veicoloradiocomandato/styles.css")).toExternalForm());
    }

    /**
     * Restituisce l'istanza di Scene associata al campo di gioco.
     *
     * @return L'istanza di Scene rappresentante il campo di gioco.
     */
    @Override
    public Scene getScene() {
        return scene;
    }

    /**
     * Restituisce il gestore dei veicoli associato al campo di gioco.
     *
     * @return L'istanza di VeicoloManager.
     */
    @Override
    public VeicoloManager getVeicoloManager() {
        return road.getVeicoloManager();
    }

    /**
     * Restituisce il gestore degli ostacoli associato al campo di gioco.
     *
     * @return L'istanza di ObstacleManager.
     */
    @Override
    public ObstacleManager getObstacleManager() {
        return road.getObstacleManager();
    }
}
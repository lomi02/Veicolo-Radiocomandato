package com.lomi.veicoloradiocomandato.Gioco;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Objects;
import java.util.Optional;

/**
 * La classe CollisionManager gestisce le collisioni tra il veicolo e gli ostacoli nel gioco.
 */
public class CollisionManager {

    private boolean collisionHandled = false;

    /**
     * Gestisce la collisione tra il veicolo e un ostacolo.
     *
     * @param gameManager  Il gestore del gioco.
     * @param newBounds    I nuovi limiti del veicolo dopo il movimento.
     * @param vehicleNode  L'immagine del veicolo.
     * @param obstacleView L'immagine dell'ostacolo.
     */
    public void handleCollision(GameManagerInterface gameManager, Bounds newBounds, ImageView vehicleNode, ImageView obstacleView) {
        // Verifica se la collisione non è già stata gestita e se i limiti del veicolo si sovrappongono a quelli dell'ostacolo sulla stessa colonna.
        if (!collisionHandled && newBounds.intersects(vehicleNode.getBoundsInParent()) && Objects.equals(GridPane.getColumnIndex(obstacleView), GridPane.getColumnIndex(vehicleNode))) {
            // Ferma il gioco.
            gameManager.stopGame();

            // Visualizza un messaggio di "Game Over" con opzioni per riprovare o uscire.
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("Vuoi riprovare?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Riprova il gioco.
                gameManager.restartGame();
            } else {
                // Esci dal gioco.
                Platform.exit();
            }
            // Imposta la collisione come gestita per evitare azioni duplicate.
            collisionHandled = true;
        }
    }

    /**
     * Reimposta lo stato delle collisioni, consentendo la gestione di nuove collisioni.
     */
    public void resetCollisions() {
        collisionHandled = false;
    }
}
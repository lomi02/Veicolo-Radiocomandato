package com.lomi.veicoloradiocomandato.Gioco;

import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Objects;
import java.util.Optional;

public class CollisionManager {
    private boolean collisionHandled = false;

    public void handleCollision(GameManagerInterface gameManager, Bounds newBounds, ImageView vehicleNode, ImageView obstacleView) {
        if (!collisionHandled && newBounds.intersects(vehicleNode.getBoundsInParent()) && Objects.equals(GridPane.getColumnIndex(obstacleView), GridPane.getColumnIndex(vehicleNode))) {
            gameManager.stopGame();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("Vuoi riprovare?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                gameManager.restartGame();
            } else {
                Platform.exit();
            }

            collisionHandled = true;
        }
    }

    public void resetCollisions() {
        collisionHandled = false;
    }
}
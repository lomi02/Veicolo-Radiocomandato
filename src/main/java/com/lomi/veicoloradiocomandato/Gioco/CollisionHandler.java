package com.lomi.veicoloradiocomandato.Gioco;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class CollisionHandler {
    private static boolean collisionHandled = false;

    public static void handleCollision(GameManagerInterface gameManager) {
        Platform.runLater(() -> {
            if (!collisionHandled) {
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
        });
    }
}
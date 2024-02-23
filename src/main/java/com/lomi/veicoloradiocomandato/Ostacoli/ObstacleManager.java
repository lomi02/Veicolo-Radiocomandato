package com.lomi.veicoloradiocomandato.Ostacoli;

import com.lomi.veicoloradiocomandato.Gioco.GameManager;
import com.lomi.veicoloradiocomandato.Scena.Road;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObstacleManager {
    private static final Logger LOGGER = Logger.getLogger(ObstacleManager.class.getName());
    private final GridPane road;
    private final List<Obstacle> obstacles;
    private final Road roadController;
    private final VeicoloManager vehicleManager;
    private final List<TranslateTransition> animations = new ArrayList<>();
    private GameManager gameManager;
    private int obstacleCounter = 0;

    public ObstacleManager(GridPane road, List<Obstacle> obstacles, Road roadController, VeicoloManager vehicleManager, GameManager gameManager) {
        this.road = road;
        this.obstacles = obstacles;
        this.roadController = roadController;
        this.vehicleManager = vehicleManager;
        this.gameManager = gameManager;
    }

    public void spawnObstacle(Random random) {
        if (obstacleCounter >= 5) return;
        try {
            Obstacle obstacle = getRandomObstacle(random);
            ImageView obstacleView = obstacle.getObstacleImage();
            obstacleView.boundsInParentProperty().addListener((observable, oldBounds, newBounds) -> {
                ImageView vehicleNode = vehicleManager.getVehicleNode();
                if (vehicleNode != null) {
                    if (newBounds.intersects(vehicleNode.getBoundsInParent())) {
                        Platform.runLater(() -> {
                            gameManager.stopGame();

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Game Over");
                            alert.setHeaderText("Vuoi riprovare?");

                            Optional<ButtonType> result = alert.showAndWait();
                            result.ifPresent(buttonType -> {
                                if (buttonType == ButtonType.OK){
                                    gameManager.restartGame();
                                } else {
                                    Platform.exit();
                                }
                            });
                        });
                    }
                }
            });

            int lane = roadController.getRandomLane(random);
            placeObstacleInView(obstacleView, lane);
            TranslateTransition transition = moveObstacleAndViewHandleEnd(obstacleView, random);
            transition.play();
            obstacleCounter++;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Errore durante lo spawn dell'ostacolo.", e);
        }
    }
    private Obstacle getRandomObstacle(Random random) {
        int index = random.nextInt(obstacles.size());
        return obstacles.get(index);
    }
    private void placeObstacleInView(ImageView obstacleView, int lane) {
        obstacleView.setTranslateY(-obstacleView.getImage().getHeight() - 200);
        road.getChildren().add(obstacleView);
        GridPane.setColumnIndex(obstacleView, lane);
    }
    private TranslateTransition moveObstacleAndViewHandleEnd(ImageView obstacleView, Random random) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(5), obstacleView);
        transition.setByY(1400);
        transition.setOnFinished(e -> endOfObstacleLifeCycle(obstacleView, random));
        animations.add(transition);
        return transition;
    }
    private void endOfObstacleLifeCycle(ImageView obstacleView, Random random) {
        road.getChildren().remove(obstacleView);
        if (obstacleCounter > 0) obstacleCounter--;
        spawnObstacle(random);
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public List<TranslateTransition> getAnimations() {
        return animations;
    }
}
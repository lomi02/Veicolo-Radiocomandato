package com.lomi.veicoloradiocomandato.Ostacoli;

import com.lomi.veicoloradiocomandato.Gioco.CollisionHandler;
import com.lomi.veicoloradiocomandato.Gioco.GameManagerInterface;
import com.lomi.veicoloradiocomandato.Scena.Road;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObstacleManager {
    private final GridPane road;
    private final List<Obstacle> obstacles;
    private final Road roadController;
    private final VeicoloManager vehicleManager;
    private final List<TranslateTransition> animations = new ArrayList<>();
    private final GameManagerInterface gameManager;
    private static final Logger LOGGER = Logger.getLogger(ObstacleManager.class.getName());
    private int obstacleCounter = 0;

    public ObstacleManager(GridPane road, List<Obstacle> obstacles, Road roadController, VeicoloManager vehicleManager, GameManagerInterface gameManager) {
        this.road = road;
        this.obstacles = obstacles;
        this.roadController = roadController;
        this.vehicleManager = vehicleManager;
        this.gameManager = gameManager;
    }

    public void spawnObstacle(Random random) {
        if (obstacleCounter >= 5 || !gameManager.isGameRunning()) return;
        try {
            Obstacle obstacle = getRandomObstacle(random);
            if (obstacle == null) {
                LOGGER.log(Level.WARNING, "Nessun ostacolo da generare");
                return;
            }

            ImageView obstacleView = obstacle.getObstacleImage();
            int lane = roadController.getRandomLane(random);
            placeObstacle(obstacleView, lane);

            obstacleView.boundsInParentProperty().addListener((observable, oldBounds, newBounds) -> Platform.runLater(() -> {
                ImageView vehicleNode = vehicleManager.getVehicleNode();
                if (vehicleNode != null && newBounds.intersects(vehicleNode.getBoundsInParent()) && Objects.equals(GridPane.getColumnIndex(obstacleView), GridPane.getColumnIndex(vehicleNode))) {
                    CollisionHandler.handleCollision(gameManager);
                }
            }));

            TranslateTransition transition = moveObstacle(obstacleView, random);
            Platform.runLater(transition::play);
            obstacleCounter++;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Errore durante lo spawn dell'ostacolo.", e);
        }
    }

    private Obstacle getRandomObstacle(Random random) {
        if (obstacles.isEmpty()) return null;
        int index = random.nextInt(obstacles.size());
        return obstacles.get(index);
    }

    private void placeObstacle(ImageView obstacleView, int lane) {
        obstacleView.setTranslateY(-obstacleView.getImage().getHeight() - 200);
        road.getChildren().add(obstacleView);
        GridPane.setColumnIndex(obstacleView, lane);
    }

    private TranslateTransition moveObstacle(ImageView obstacleView, Random random) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(5), obstacleView);
        transition.setByY(1400);
        transition.setOnFinished(e -> killObstacle(obstacleView, random));
        animations.add(transition);
        return transition;
    }

    private void killObstacle(ImageView obstacleView, Random random) {
        road.getChildren().remove(obstacleView);
        if (obstacleCounter > 0) obstacleCounter--;
        spawnObstacle(random);
    }

    public List<TranslateTransition> getAnimations() {
        return animations;
    }
}
package com.lomi.veicoloradiocomandato.Ostacoli;

import com.lomi.veicoloradiocomandato.Gioco.CollisionManager;
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
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObstacleManager {
    private final GridPane road;
    private final List<Obstacle> obstacles;
    private final Road roadController;
    private final VeicoloManager vehicleManager;
    private final List<TranslateTransition> animations = new ArrayList<>();
    private final GameManagerInterface gameManager;
    private ScheduledExecutorService obstacleSpawner = Executors.newSingleThreadScheduledExecutor();
    private static final Logger LOGGER = Logger.getLogger(ObstacleManager.class.getName());

    public ObstacleManager(GridPane road, List<Obstacle> obstacles, Road roadController, VeicoloManager vehicleManager, GameManagerInterface gameManager) {
        this.road = road;
        this.obstacles = obstacles;
        this.roadController = roadController;
        this.vehicleManager = vehicleManager;
        this.gameManager = gameManager;
    }

    public void spawnObstacle(Random random) {
        if (!gameManager.isGameRunning()) return;
        try {
            Obstacle obstacle = getRandomObstacle(random);
            if (obstacle == null) {
                LOGGER.log(Level.WARNING, "Nessun ostacolo da generare");
                return;
            }

            ImageView obstacleView = obstacle.getObstacleImage();
            int lane = roadController.getRandomLane(random);

            Platform.runLater(() -> {
                placeObstacle(obstacleView, lane);

                CollisionManager collisionManager = gameManager.getCollisionManager();
                obstacleView.boundsInParentProperty().addListener((observable, oldBounds, newBounds) -> Platform.runLater(() -> {
                    ImageView vehicleNode = vehicleManager.getVehicleNode();
                    if (vehicleNode != null) {
                        collisionManager.handleCollision(gameManager, newBounds, vehicleNode, obstacleView);
                    }
                }));

                TranslateTransition transition = moveObstacle(obstacleView);
                transition.play();
            });
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

    private TranslateTransition moveObstacle(ImageView obstacleView) {
        double speedFactor = gameManager.getRadiocomando().getMarciaAttuale();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(7.5 / speedFactor), obstacleView);
        transition.setByY(1400);
        transition.setOnFinished(e -> killObstacle(obstacleView));
        animations.add(transition);
        return transition;
    }

    private void killObstacle(ImageView obstacleView) {
        road.getChildren().remove(obstacleView);
    }

    public void startObstacleGeneration() {
        obstacleSpawner = Executors.newSingleThreadScheduledExecutor();
        long speedFactor = gameManager.getRadiocomando().getMarciaAttuale();
        long msInterval = speedFactor * 5000;
        obstacleSpawner.scheduleAtFixedRate(() -> spawnObstacle(new Random()), 1, msInterval, TimeUnit.MILLISECONDS);    }

    public void stopObstacleGeneration() {
        obstacleSpawner.shutdown();
    }

    public List<TranslateTransition> getAnimations() {
        return animations;
    }
}
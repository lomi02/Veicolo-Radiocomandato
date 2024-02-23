package com.lomi.veicoloradiocomandato.Ostacoli;

import com.lomi.veicoloradiocomandato.Gioco.Road;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObstacleManager {
    private static final Logger LOGGER = Logger.getLogger(ObstacleManager.class.getName());
    private final GridPane road;
    private final List<Obstacle> obstacles;
    private final Road roadController;
    private int obstacleCounter = 0;

    public ObstacleManager(GridPane road, List<Obstacle> obstacles, Road roadController) {
        this.road = road;
        this.obstacles = obstacles;
        this.roadController = roadController;
    }

    public void spawnObstacle(Random random) {
        if (obstacleCounter >= 5) return;

        try {
            Obstacle obstacle = getRandomObstacle(random);
            ImageView obstacleView = obstacle.getObstacleImage();
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
        return transition;
    }
    private void endOfObstacleLifeCycle(ImageView obstacleView, Random random) {
        road.getChildren().remove(obstacleView);
        if (obstacleCounter > 0) obstacleCounter--;
        spawnObstacle(random);
    }
}
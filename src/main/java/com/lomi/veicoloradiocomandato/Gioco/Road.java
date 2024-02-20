package com.lomi.veicoloradiocomandato.Gioco;

import com.lomi.veicoloradiocomandato.Ostacoli.GestoreOstacoli;
import com.lomi.veicoloradiocomandato.Ostacoli.Obstacle;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Road extends DayNightCycle {
    private static final Logger LOGGER = Logger.getLogger(Road.class.getName());
    private static final String ROAD_FXML_PATH = "/com/lomi/veicoloradiocomandato/road.fxml";
    private static final String OBSTACLES_JSON_PATH = "/com/lomi/veicoloradiocomandato/storage.json";
    private final GridPane road;
    private final List<Obstacle> obstacles;
    private final Rectangle lane1;
    private final Rectangle lane2;
    private final Rectangle lane3;

    public Road() {
        try {
            FXMLLoader fxmlLoader = loadFXML(ROAD_FXML_PATH);
            RoadController controller = fxmlLoader.getController();
            road = controller.getRoad();
            lane1 = controller.getLane1();
            lane2 = controller.getLane2();
            lane3 = controller.getLane3();
            if (lane1 != null && lane2 != null && lane3 != null && road != null) {
                initializeDayNightCycle();
            } else {
                LOGGER.log(Level.SEVERE, "One or more lanes or the road were not correctly initialized.");
                throw new RuntimeException("Failed to initialize the road and lanes.");
            }

            GestoreOstacoli gestoreOstacoli = new GestoreOstacoli(OBSTACLES_JSON_PATH);
            this.obstacles = gestoreOstacoli.getObstacles();
            spawnObstacle();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to create Road.", e);
            throw new RuntimeException("Failed to create Road.", e);
        }
    }

    @Override
    public void initializeDayNightCycle() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(lane1.fillProperty(), Color.GRAY),
                        new KeyValue(lane2.fillProperty(), Color.GRAY),
                        new KeyValue(lane3.fillProperty(), Color.GRAY)
                ),
                new KeyFrame(Duration.seconds(10),
                        new KeyValue(lane1.fillProperty(), Color.DARKSLATEGRAY),
                        new KeyValue(lane2.fillProperty(), Color.DARKSLATEGRAY),
                        new KeyValue(lane3.fillProperty(), Color.DARKSLATEGRAY)
                ),
                new KeyFrame(Duration.seconds(20),
                        new KeyValue(lane1.fillProperty(), Color.GRAY),
                        new KeyValue(lane2.fillProperty(), Color.GRAY),
                        new KeyValue(lane3.fillProperty(), Color.GRAY)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void spawnObstacle() {
        try {
            if (obstacles.isEmpty()) {
                LOGGER.log(Level.WARNING, "No obstacles found. Check your JSON file.");
                return;
            }

            Random rand = new Random();
            int randomObstacleIndex = rand.nextInt(obstacles.size());
            Obstacle randomObstacle = obstacles.get(randomObstacleIndex);

            ImageView obstacleView = randomObstacle.getImageView();
            if (obstacleView == null) {
                LOGGER.log(Level.SEVERE, "Failed to load obstacle image. Obstacle ImageView is null.");
                return;
            }

            int lane = rand.nextInt(3);

            if (road == null) {
                LOGGER.log(Level.SEVERE, "Failed to add obstacle. Road is not initialized.");
                return;
            }
            GridPane.setColumnIndex(obstacleView, lane);
            road.getChildren().add(obstacleView);

            Timeline timeline = new Timeline();

            if (road.getHeight() <= 0) {
                LOGGER.log(Level.SEVERE, "Road height is not valid.");
                return;
            }

            KeyValue kv = new KeyValue(obstacleView.layoutYProperty(), road.getHeight());
            KeyFrame kf = new KeyFrame(Duration.seconds(5), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(e -> {
                if (obstacleView.getLayoutY() >= road.getHeight()) {
                    road.getChildren().remove(obstacleView);
                }
                spawnObstacle();
            });
            timeline.play();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to spawn obstacle.", e);
            throw new RuntimeException("Failed to spawn obstacle.", e);
        }
    }

    public GridPane getRoad() {
        return road;
    }
}
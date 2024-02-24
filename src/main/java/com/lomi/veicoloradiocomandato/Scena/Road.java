package com.lomi.veicoloradiocomandato.Scena;

import com.lomi.veicoloradiocomandato.Gioco.GameManagerInterface;
import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleFetcher;
import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Road extends DayNightCycle {
    private static final Logger LOGGER = Logger.getLogger(Road.class.getName());
    private static final String ROAD_FXML_PATH = "/com/lomi/veicoloradiocomandato/road.fxml";
    private final ObstacleManager obstacleManager;
    private final VeicoloManager vehicleManager;
    private GridPane road;
    private Rectangle lane1;
    private Rectangle lane2;
    private Rectangle lane3;
    private static final int[] lanes = {1, 3, 5};

    public Road(String chosenVehicle, GameManagerInterface gameManager) {
        try {
            initializeFXML();
            initializeDayNightCycle();

            ObstacleFetcher obstacleFetcher = new ObstacleFetcher();
            this.vehicleManager = new VeicoloManager(road);
            this.obstacleManager = new ObstacleManager(road, obstacleFetcher.getObstacles(), this, vehicleManager, gameManager);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> obstacleManager.spawnObstacle(new Random()));
                    Platform.runLater(() -> vehicleManager.spawnVeicolo(chosenVehicle));
                }
            }, 0, 3000);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to create Road.", e);
            throw new RuntimeException("Failed to create Road.", e);
        }
    }

    private void checkInitialization(Rectangle lane1, Rectangle lane2, Rectangle lane3, GridPane road) {
        if (lane1 == null || lane2 == null || lane3 == null || road == null) {
            LOGGER.log(Level.SEVERE, "Una o più corsie o la strada non sono state correttamente inizializzate.");
            throw new RuntimeException("Fallimento durante l'inizializzazione di strada e corsie.");
        }
    }

    private void initializeFXML() {
        FXMLLoader fxmlLoader = loadFXML(ROAD_FXML_PATH);
        RoadController controller = fxmlLoader.getController();

        road = controller.getRoad();
        lane1 = controller.getLane1();
        lane2 = controller.getLane2();
        lane3 = controller.getLane3();

        checkInitialization(lane1, lane2, lane3, road);
    }

    public int getRandomLane(Random random) {
        return lanes[random.nextInt(lanes.length)];
    }

    @Override
    protected void initializeDayNightCycle() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(lane1.fillProperty(), Color.SLATEGRAY),
                        new KeyValue(lane2.fillProperty(), Color.SLATEGRAY),
                        new KeyValue(lane3.fillProperty(), Color.SLATEGRAY)
                ),
                new KeyFrame(Duration.seconds(10),
                        new KeyValue(lane1.fillProperty(), Color.SLATEGRAY),
                        new KeyValue(lane2.fillProperty(), Color.SLATEGRAY),
                        new KeyValue(lane3.fillProperty(), Color.SLATEGRAY)
                ),
                new KeyFrame(Duration.seconds(15),
                        new KeyValue(lane1.fillProperty(), Color.DARKSLATEGRAY),
                        new KeyValue(lane2.fillProperty(), Color.DARKSLATEGRAY),
                        new KeyValue(lane3.fillProperty(), Color.DARKSLATEGRAY)
                ),
                new KeyFrame(Duration.seconds(25),
                        new KeyValue(lane1.fillProperty(), Color.DARKSLATEGRAY),
                        new KeyValue(lane2.fillProperty(), Color.DARKSLATEGRAY),
                        new KeyValue(lane3.fillProperty(), Color.DARKSLATEGRAY)
                ),
                new KeyFrame(Duration.seconds(30),
                        new KeyValue(lane1.fillProperty(), Color.SLATEGRAY),
                        new KeyValue(lane2.fillProperty(), Color.SLATEGRAY),
                        new KeyValue(lane3.fillProperty(), Color.SLATEGRAY)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public GridPane getRoad() {
        return road;
    }

    public ObstacleManager getObstacleManager() {
        return this.obstacleManager;
    }
}
package com.lomi.veicoloradiocomandato.Scena;

import com.lomi.veicoloradiocomandato.Gioco.GameManagerInterface;
import com.lomi.veicoloradiocomandato.Ostacoli.Obstacle;
import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleFetcher;
import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
import com.lomi.veicoloradiocomandato.Vehicle.Veicolo;
import com.lomi.veicoloradiocomandato.Vehicle.VeicoloManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Road extends DayNightCycle {
    private static final Logger LOGGER = Logger.getLogger(Road.class.getName());
    private static final String ROAD_FXML_PATH = "/com/lomi/veicoloradiocomandato/road.fxml";
    private final VeicoloManager vehicleManager;
    private final ObstacleManager obstacleManager;
    private final GridPane road;
    private final Rectangle lane1;
    private final Rectangle lane2;
    private final Rectangle lane3;
    private static final int[] lanes = {1, 3, 5};

    public Road(String chosenVehicle, GameManagerInterface gameManager) {
        try {
            RoadController controller = new RoadController();
            try {
                gameManager.loadFXML(ROAD_FXML_PATH, controller);
            } catch(IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to load " + ROAD_FXML_PATH, e);
                throw new RuntimeException(e);
            }
            road = controller.getRoad();
            lane1 = controller.getLane1();
            lane2 = controller.getLane2();
            lane3 = controller.getLane3();
            checkInitialization(lane1, lane2, lane3, road);

            initializeDayNightCycle();

            ObstacleFetcher obstacleFetcher = new ObstacleFetcher();
            List<Obstacle> obstacles = obstacleFetcher.getObstacles();
            this.vehicleManager = new VeicoloManager(road, obstacles, gameManager);
            this.obstacleManager = new ObstacleManager(road, obstacles, this, vehicleManager, gameManager);
            vehicleManager.spawnVeicolo(chosenVehicle);
            obstacleManager.spawnObstacle(new Random());

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

    public int getRandomLane(Random random) {
        return lanes[random.nextInt(lanes.length)];
    }

    @Override
    protected void initializeDayNightCycle() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(START_TIME),
                        new KeyValue(lane1.fillProperty(), Color.SLATEGRAY),
                        new KeyValue(lane2.fillProperty(), Color.SLATEGRAY),
                        new KeyValue(lane3.fillProperty(), Color.SLATEGRAY)
                ),
                new KeyFrame(Duration.seconds(DAY_TIME),
                        new KeyValue(lane1.fillProperty(), Color.SLATEGRAY),
                        new KeyValue(lane2.fillProperty(), Color.SLATEGRAY),
                        new KeyValue(lane3.fillProperty(), Color.SLATEGRAY)
                ),
                new KeyFrame(Duration.seconds(TWILIGHT_TIME),
                        new KeyValue(lane1.fillProperty(), Color.DARKSLATEGRAY),
                        new KeyValue(lane2.fillProperty(), Color.DARKSLATEGRAY),
                        new KeyValue(lane3.fillProperty(), Color.DARKSLATEGRAY)
                ),
                new KeyFrame(Duration.seconds(NIGHT_TIME),
                        new KeyValue(lane1.fillProperty(), Color.DARKSLATEGRAY),
                        new KeyValue(lane2.fillProperty(), Color.DARKSLATEGRAY),
                        new KeyValue(lane3.fillProperty(), Color.DARKSLATEGRAY)
                ),
                new KeyFrame(Duration.seconds(END_TIME),
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
    public Veicolo getVeicolo() {
        return vehicleManager.getVeicolo();
    }
    public VeicoloManager getVeicoloManager() {
        return this.vehicleManager;
    }
    public ObstacleManager getObstacleManager() {
        return this.obstacleManager;
    }
}
package com.lomi.veicoloradiocomandato.Scena;

import com.lomi.veicoloradiocomandato.Gioco.GameManagerInterface;
import com.lomi.veicoloradiocomandato.Ostacoli.Obstacle;
import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleFetcher;
import com.lomi.veicoloradiocomandato.Ostacoli.ObstacleManager;
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

/**
 * La classe Road estende la classe DayNightCycle e rappresenta la strada del gioco.
 */
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

    /**
     * Costruttore della classe Road.
     *
     * @param chosenVehicle Il veicolo scelto dal giocatore.
     * @param gameManager   L'interfaccia del gestore di gioco.
     */
    public Road(String chosenVehicle, GameManagerInterface gameManager) {
        try {
            RoadController controller = new RoadController();
            try {
                gameManager.loadFXML(ROAD_FXML_PATH, controller);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Impossibile caricare " + ROAD_FXML_PATH, e);
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
            LOGGER.log(Level.SEVERE, "Impossibile creare Road.", e);
            throw new RuntimeException("Impossibile creare Road.", e);
        }
    }

    /**
     * Verifica che le corsie e la strada siano correttamente inizializzate.
     *
     * @param lane1 La prima corsia.
     * @param lane2 La seconda corsia.
     * @param lane3 La terza corsia.
     * @param road  La strada.
     */
    private void checkInitialization(Rectangle lane1, Rectangle lane2, Rectangle lane3, GridPane road) {
        if (lane1 == null || lane2 == null || lane3 == null || road == null) {
            LOGGER.log(Level.SEVERE, "Una o più corsie o la strada non sono state correttamente inizializzate.");
            throw new RuntimeException("Fallimento durante l'inizializzazione di strada e corsie.");
        }
    }

    /**
     * Restituisce un numero casuale rappresentante una delle corsie disponibili.
     *
     * @param random Oggetto Random per generare il numero casuale.
     * @return Il numero della corsia scelta casualmente.
     */
    public int getRandomLane(Random random) {
        return lanes[random.nextInt(lanes.length)];
    }

    /**
     * Inizializza il ciclo giorno-notte con le animazioni dei colori delle corsie.
     */
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

    /**
     * Restituisce la griglia della strada.
     *
     * @return L'istanza di GridPane rappresentante la strada.
     */
    public GridPane getRoad() {
        return road;
    }

    /**
     * Restituisce il gestore dei veicoli associato alla strada.
     *
     * @return L'istanza di VeicoloManager.
     */
    public VeicoloManager getVeicoloManager() {
        return this.vehicleManager;
    }

    /**
     * Restituisce il gestore degli ostacoli associato alla strada.
     *
     * @return L'istanza di ObstacleManager.
     */
    public ObstacleManager getObstacleManager() {
        return this.obstacleManager;
    }
}
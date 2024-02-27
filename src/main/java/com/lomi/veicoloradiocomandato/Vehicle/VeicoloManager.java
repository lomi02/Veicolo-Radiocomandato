package com.lomi.veicoloradiocomandato.Vehicle;

import com.lomi.veicoloradiocomandato.Gioco.GameManagerInterface;
import com.lomi.veicoloradiocomandato.Ostacoli.Obstacle;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VeicoloManager {
    private static final Logger LOGGER = Logger.getLogger(VeicoloManager.class.getName());
    private final GridPane road;
    private Veicolo veicolo;
    private ImageView veicoloView;
    private final List<Obstacle> obstacles;
    private final List<TranslateTransition> animations = new ArrayList<>();

    public boolean isAnimating() {
        return animations.stream().anyMatch(a -> a.getStatus() == Animation.Status.RUNNING);
    }

    private final GameManagerInterface gameManager;

    public VeicoloManager(GridPane road, List<Obstacle> obstacles, GameManagerInterface gameManager) {
        this.road = road;
        this.obstacles = obstacles;
        this.gameManager = gameManager;
    }

    public void spawnVeicolo(String veicoloScelto) {
        try {
            VeicoloFetcher veicoloFetcher = new VeicoloFetcher();
            this.veicolo = veicoloFetcher.getVeicoloPerMarca(veicoloScelto);
            if (veicolo != null) {
                this.veicoloView = veicolo.getVehicleImage();
                placeVehicleInView(veicoloView);
            } else {
                LOGGER.log(Level.SEVERE, "Error during vehicle spawn. Not found vehicle: " + veicoloScelto);
            }
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "NullPointerException caught. One or more objects were not initialized properly.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during vehicle spawn.", e);
        }
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public ImageView getVehicleNode() {
        return veicoloView;
    }

    public void spostaVeicolo(String direzione) {
        int currentLane = GridPane.getColumnIndex(veicoloView);
        int targetLane = getTargetLane(currentLane, direzione);

        TranslateTransition transition = createTransition(targetLane);
        AnimationTimer collisionTimer = createCollisionTimer();

        transition.statusProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Animation.Status.RUNNING) {
                collisionTimer.start();
            } else if (newValue == Animation.Status.STOPPED) {
                collisionTimer.stop();
            }
        });

        animations.add(transition);
        transition.play();
    }

    private void placeVehicleInView(ImageView veicoloView) {
        road.getChildren().add(veicoloView);
        GridPane.setColumnIndex(veicoloView, 3);
        double position = 700 - veicoloView.getImage().getHeight() * 1.2;
        veicoloView.setTranslateY(position);
    }

    private int getTargetLane(int currentLane, String direzione) {
        int targetLane = currentLane;
        if ("destra".equals(direzione) && currentLane < 5) {
            targetLane += 2;
        } else if ("sinistra".equals(direzione) && currentLane > 1) {
            targetLane -= 2;
        }
        return targetLane;
    }

    private TranslateTransition createTransition(int targetLane) {
        double speedFactor = gameManager.getRadiocomando().getMarciaAttuale();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5 / speedFactor), veicoloView);

        transition.setToX((targetLane - GridPane.getColumnIndex(veicoloView)) * 30);

        transition.setOnFinished(e -> {
            veicoloView.setTranslateX(0);
            GridPane.setColumnIndex(veicoloView, targetLane);
        });

        return transition;
    }

    private AnimationTimer createCollisionTimer() {
        return new AnimationTimer() {
            @Override
            public void handle(long now) {
                Bounds bounds = veicoloView.getBoundsInParent();
                obstacles.forEach(obstacle -> {
                    ImageView obstacleView = obstacle.getObstacleImage();
                    if (bounds.intersects(obstacleView.getBoundsInParent())) {
                        gameManager.getCollisionManager().handleCollision(gameManager, bounds, veicoloView, obstacleView);
                        this.stop();
                    }
                });
            }
        };
    }

    public List<TranslateTransition> getAnimations() {
        return animations;
    }
}
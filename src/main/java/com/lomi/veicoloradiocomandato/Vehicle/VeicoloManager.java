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

/**
 * Classe per gestire le funzionalità del veicolo nel gioco.
 */
public class VeicoloManager {
    private static final Logger LOGGER = Logger.getLogger(VeicoloManager.class.getName());
    private final GridPane road;
    private ImageView veicoloView;
    private final List<Obstacle> obstacles;
    private final List<TranslateTransition> animations = new ArrayList<>();
    private final GameManagerInterface gameManager;

    /**
     * Costruttore di VeicoloManager.
     *
     * @param road         la strada su cui si muove il veicolo
     * @param obstacles    gli ostacoli presenti sulla strada
     * @param gameManager  l'interfaccia del gestore del gioco
     */
    public VeicoloManager(GridPane road, List<Obstacle> obstacles, GameManagerInterface gameManager) {
        this.road = road;
        this.obstacles = obstacles;
        this.gameManager = gameManager;
    }

    /**
     * Metodo per generare un veicolo nel gioco.
     *
     * @param veicoloScelto il tipo di veicolo scelto dal giocatore
     */
    public void spawnVeicolo(String veicoloScelto) {
        try {
            VeicoloFetcher veicoloFetcher = new VeicoloFetcher();
            Veicolo veicolo = veicoloFetcher.getVeicoloPerMarca(veicoloScelto);
            if (veicolo != null) {
                this.veicoloView = veicolo.getVehicleImage();
                generaVistaVeicolo(veicoloView);
            } else {
                LOGGER.log(Level.SEVERE, "Error during vehicle spawn. Not found vehicle: " + veicoloScelto);
            }
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "NullPointerException caught. One or more objects were not initialized properly.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during vehicle spawn.", e);
        }
    }

    /**
     * Metodo per muovere il veicolo.
     *
     * @param direzione la direzione in cui si desidera muovere il veicolo
     */
    public void muoviVeicolo(String direzione) {
        int currentLane = GridPane.getColumnIndex(veicoloView);
        int targetLane = getDestinazione(currentLane, direzione);

        TranslateTransition transition = transizioneMovimento(targetLane);
        AnimationTimer collisionTimer = creaFinestraCollisione();

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

    /**
     * Metodo per generare la vista del veicolo.
     *
     * @param veicoloView la vista del veicolo
     */
    private void generaVistaVeicolo(ImageView veicoloView) {
        road.getChildren().add(veicoloView);
        GridPane.setColumnIndex(veicoloView, 3);
        double position = 700 - veicoloView.getImage().getHeight() * 1.2;
        veicoloView.setTranslateY(position);
    }

    /**
     * Metodo per ottenere la destinazione del veicolo.
     *
     * @param currentLane la corsia attuale del veicolo
     * @param direzione   la direzione in cui si desidera muovere il veicolo
     * @return la corsia di destinazione
     */
    private int getDestinazione(int currentLane, String direzione) {
        int targetLane = currentLane;
        if ("destra".equals(direzione) && currentLane < 5) {
            targetLane += 2;
        } else if ("sinistra".equals(direzione) && currentLane > 1) {
            targetLane -= 2;
        }
        return targetLane;
    }

    /**
     * Metodo per creare una transizione di movimento.
     *
     * @param targetLane la corsia di destinazione
     * @return la transizione di movimento
     */
    private TranslateTransition transizioneMovimento(int targetLane) {
        double speedFactor = gameManager.getRadiocomando().getMarciaAttuale();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5 / speedFactor), veicoloView);

        transition.setToX((targetLane - GridPane.getColumnIndex(veicoloView)) * 30);

        transition.setOnFinished(e -> {
            veicoloView.setTranslateX(0);
            GridPane.setColumnIndex(veicoloView, targetLane);
        });

        return transition;
    }

    /**
     * Metodo per creare un timer di collisione.
     *
     * @return il timer di collisione
     */
    private AnimationTimer creaFinestraCollisione() {
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

    /**
     * Metodo per ottenere la vista del veicolo.
     *
     * @return la vista del veicolo
     */
    public ImageView getVistaVeicolo() {
        return veicoloView;
    }

    /**
     * Metodo per ottenere le animazioni di movimento.
     *
     * @return le animazioni di movimento
     */
    public List<TranslateTransition> getAnimations() {
        return animations;
    }

    /**
     * Metodo per ottenere il bool che esplicita se un animazione è in corso.
     *
     * @return true se ancora in corso, false altrimenti
     */
    public boolean isAnimating() {
        return animations.stream().anyMatch(a -> a.getStatus() == Animation.Status.RUNNING);
    }
}
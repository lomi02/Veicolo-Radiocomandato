package com.lomi.veicoloradiocomandato.Scena;

import com.lomi.veicoloradiocomandato.Gioco.GameManagerInterface;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe Background estende la classe DayNightCycle e rappresenta lo sfondo del gioco.
 */
public class Background extends DayNightCycle {

    private static final Logger LOGGER = Logger.getLogger(Background.class.getName());
    private static final String BACKGROUND_FXML_PATH = "/com/lomi/veicoloradiocomandato/background.fxml";

    /**
     * Costruttore della classe Background.
     *
     * @param gameManager L'interfaccia del gestore di gioco.
     */
    public Background(GameManagerInterface gameManager) {
        try {
            BackgroundController controller = new BackgroundController();
            try {
                gameManager.loadFXML(BACKGROUND_FXML_PATH, controller);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Impossibile caricare " + BACKGROUND_FXML_PATH, e);
                throw new RuntimeException(e);
            }
            rectangle = controller.fetchBackground();
            if (rectangle != null) {
                initializeDayNightCycle();
            } else {
                throw new RuntimeException("Lo sfondo non è stato inizializzato correttamente.");
            }
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "Impossibile caricare " + BACKGROUND_FXML_PATH, e);
        }
    }

    /**
     * Inizializza il ciclo giorno-notte con le animazioni dei colori dello sfondo.
     */
    @Override
    protected void initializeDayNightCycle() {
        Timeline dayNightCycle = new Timeline(
                new KeyFrame(Duration.seconds(START_TIME), new KeyValue(rectangle.fillProperty(), Color.GREEN)),
                new KeyFrame(Duration.seconds(DAY_TIME), new KeyValue(rectangle.fillProperty(), Color.GREEN)),
                new KeyFrame(Duration.seconds(TWILIGHT_TIME), new KeyValue(rectangle.fillProperty(), Color.BLACK)),
                new KeyFrame(Duration.seconds(NIGHT_TIME), new KeyValue(rectangle.fillProperty(), Color.BLACK)),
                new KeyFrame(Duration.seconds(END_TIME), new KeyValue(rectangle.fillProperty(), Color.GREEN))
        );

        dayNightCycle.setCycleCount(Timeline.INDEFINITE);
        dayNightCycle.play();
    }

    /**
     * Restituisce l'istanza di Rectangle associata allo sfondo.
     *
     * @return L'istanza di Rectangle rappresentante lo sfondo.
     */
    public Rectangle getBackground() {
        return rectangle;
    }
}
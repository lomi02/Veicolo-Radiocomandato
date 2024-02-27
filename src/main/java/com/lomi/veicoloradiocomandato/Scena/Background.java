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

public class Background extends DayNightCycle {
    private static final Logger LOGGER = Logger.getLogger(Background.class.getName());
    private static final String BACKGROUND_FXML_PATH = "/com/lomi/veicoloradiocomandato/background.fxml";

    public Background(GameManagerInterface gameManager) {
        try {
            BackgroundController controller = new BackgroundController();
            try {
                gameManager.loadFXML(BACKGROUND_FXML_PATH, controller);
            } catch(IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to load " + BACKGROUND_FXML_PATH, e);
                throw new RuntimeException(e);
            }
            rectangle = controller.fetchBackground();
            if (rectangle != null) {
                initializeDayNightCycle();
            } else {
                throw new RuntimeException("The background was not correctly initialized.");
            }
        } catch (RuntimeException e) {
            LOGGER.log(Level.SEVERE, "Failed to load " + BACKGROUND_FXML_PATH, e);
        }
    }

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

    public Rectangle getBackground() {
        return rectangle;
    }
}
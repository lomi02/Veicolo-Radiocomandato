package com.lomi.veicoloradiocomandato.Scena;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Background extends DayNightCycle {
    private static final Logger LOGGER = Logger.getLogger(Background.class.getName());
    private static final String BACKGROUND_FXML_PATH = "/com/lomi/veicoloradiocomandato/background.fxml";

    public Background() {
        try {
            FXMLLoader fxmlLoader = super.loadFXML(BACKGROUND_FXML_PATH);
            BackgroundController controller = fxmlLoader.getController();
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
                new KeyFrame(Duration.seconds(0), new KeyValue(rectangle.fillProperty(), Color.GREEN)),
                new KeyFrame(Duration.seconds(10), new KeyValue(rectangle.fillProperty(), Color.GREEN)),
                new KeyFrame(Duration.seconds(15), new KeyValue(rectangle.fillProperty(), Color.BLACK)),
                new KeyFrame(Duration.seconds(25), new KeyValue(rectangle.fillProperty(), Color.BLACK)),
                new KeyFrame(Duration.seconds(30), new KeyValue(rectangle.fillProperty(), Color.GREEN))
        );

        dayNightCycle.setCycleCount(Timeline.INDEFINITE);
        dayNightCycle.play();
    }

    public Rectangle getBackground() {
        return rectangle;
    }
}
package com.lomi.veicoloradiocomandato.Gioco;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class DayNightCycle {
    private final Rectangle background;

    public DayNightCycle() {
        background = new Rectangle(800, 600);
        background.setFill(Color.GREEN);

        Timeline dayNightCycle = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(background.fillProperty(), Color.GREEN)),
                new KeyFrame(Duration.seconds(10), new KeyValue(background.fillProperty(), Color.BLACK)),
                new KeyFrame(Duration.seconds(20), new KeyValue(background.fillProperty(), Color.GREEN))
        );
        dayNightCycle.setCycleCount(Timeline.INDEFINITE);
        dayNightCycle.play();
    }

    public Rectangle getBackground() {
        return background;
    }
}
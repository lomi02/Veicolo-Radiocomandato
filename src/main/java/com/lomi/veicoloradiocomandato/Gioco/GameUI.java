package com.lomi.veicoloradiocomandato.Gioco;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameUI {
    private static final String UI_FXML_PATH = "/com/lomi/veicoloradiocomandato/ui.fxml";
    private static final Logger LOGGER = Logger.getLogger(GameUI.class.getName());
    private final GameManagerInterface gameManager;
    private int gameTimer = 0;
    private int score = 0;
    private Timeline timeline;

    @FXML
    private VBox vbox;
    @FXML
    private Label timerLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label gearLabel;

    public GameUI(GameManagerInterface gameManager) {
        this.gameManager = gameManager;
        try {
            gameManager.loadFXML(UI_FXML_PATH, this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load " + UI_FXML_PATH, e);
        }
    }

    public void iniziaTimerUI() {
        this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> aggiornaUI())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void aggiornaUI() {
        gameTimer++;
        timerLabel.setText("Tempo: " + gameTimer + "s");

        int currentGear = gameManager.getRadiocomando().getMarciaAttuale();
        score += (int) Math.pow(2, currentGear);
        scoreLabel.setText("Punteggio: " + score);

        gearLabel.setText("Marcia: " + currentGear);
    }

    public void stopTimer() {
        if (this.timeline != null) {
            this.timeline.stop();
        }
    }

    public VBox getUI() {
        return vbox;
    }
}
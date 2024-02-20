package com.lomi.veicoloradiocomandato.Gioco;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class RoadController {
    @FXML
    private GridPane road;
    @FXML
    private Rectangle lane1;
    @FXML
    private Rectangle lane2;
    @FXML
    private Rectangle lane3;

    public GridPane getRoad() {
        return road;
    }

    public Rectangle getLane1() {
        return lane1;
    }

    public Rectangle getLane2() {
        return lane2;
    }

    public Rectangle getLane3() {
        return lane3;
    }
}
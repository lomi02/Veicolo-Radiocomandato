package com.lomi.veicoloradiocomandato.Scena;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

/**
 * La classe RoadController rappresenta il controller associato alla vista della strada.
 */
public class RoadController {

    @FXML
    private GridPane road;

    @FXML
    private Rectangle lane1;

    @FXML
    private Rectangle lane2;

    @FXML
    private Rectangle lane3;

    /**
     * Restituisce la griglia della strada.
     *
     * @return L'istanza di GridPane rappresentante la strada.
     */
    public GridPane getRoad() {
        return road;
    }

    /**
     * Restituisce il rettangolo rappresentante la prima corsia della strada.
     *
     * @return L'istanza di Rectangle della prima corsia.
     */
    public Rectangle getLane1() {
        return lane1;
    }

    /**
     * Restituisce il rettangolo rappresentante la seconda corsia della strada.
     *
     * @return L'istanza di Rectangle della seconda corsia.
     */
    public Rectangle getLane2() {
        return lane2;
    }

    /**
     * Restituisce il rettangolo rappresentante la terza corsia della strada.
     *
     * @return L'istanza di Rectangle della terza corsia.
     */
    public Rectangle getLane3() {
        return lane3;
    }
}
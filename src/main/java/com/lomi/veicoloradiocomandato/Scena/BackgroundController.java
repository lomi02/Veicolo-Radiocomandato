package com.lomi.veicoloradiocomandato.Scena;

import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;

/**
 * La classe BackgroundController rappresenta il controller associato alla vista dello sfondo.
 */
public class BackgroundController {

    @FXML
    private Rectangle background;

    /**
     * Restituisce l'istanza di Rectangle associata allo sfondo.
     *
     * @return L'istanza di Rectangle rappresentante lo sfondo.
     */
    public Rectangle fetchBackground() {
        return background;
    }
}
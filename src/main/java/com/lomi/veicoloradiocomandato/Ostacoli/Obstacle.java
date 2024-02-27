package com.lomi.veicoloradiocomandato.Ostacoli;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe astratta Obstacle rappresenta un generico ostacolo nel gioco.
 */
public abstract class Obstacle {

    private static final Logger LOGGER = Logger.getLogger(Obstacle.class.getName());
    private static final String OBSTACLE_FXML_PATH = "/com/lomi/veicoloradiocomandato/obstacle.fxml";
    protected String nome;
    protected Image immagine;

    /**
     * Costruttore della classe Obstacle.
     *
     * @param nome     Il nome dell'ostacolo.
     * @param immagine Il percorso dell'immagine associata all'ostacolo.
     */
    public Obstacle(String nome, String immagine) {
        this.nome = nome;

        try {
            InputStream inputStream = getClass().getResourceAsStream("/com/lomi/veicoloradiocomandato/img/" + immagine);

            if (inputStream == null) {
                LOGGER.log(Level.SEVERE, "Risorsa non trovata: " + immagine);
                throw new FileNotFoundException("Risorsa non trovata: " + immagine);
            }
            this.immagine = new Image(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problema nel caricamento dell'immagine", e);
        }
    }

    /**
     * Restituisce un'ImageView associata all'immagine dell'ostacolo.
     *
     * @return Un'ImageView con l'immagine dell'ostacolo.
     */
    public ImageView getObstacleImage() {
        ImageView imageView;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(OBSTACLE_FXML_PATH));
            imageView = loader.load();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problema nel caricamento del file FXML.", e);
            imageView = new ImageView();
        }

        if (this.immagine != null) {
            imageView.setImage(this.immagine);
        }

        return imageView;
    }
}
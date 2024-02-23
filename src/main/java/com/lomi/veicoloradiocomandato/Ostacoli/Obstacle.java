package com.lomi.veicoloradiocomandato.Ostacoli;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class Obstacle {
    private static final Logger LOGGER = Logger.getLogger(Obstacle.class.getName());
    private static final String OBSTACLE_FXML_PATH = "/com/lomi/veicoloradiocomandato/obstacle.fxml";
    private Image image;
    protected String nome;
    protected String collisione;

    public Obstacle(String nome, String immagine, String collisione) {
        this.nome = nome;
        this.collisione = collisione;
        this.image = null;
        try {
            InputStream inputStream = getClass().getResourceAsStream("/com/lomi/veicoloradiocomandato/img/" + immagine);

            if (inputStream == null) {
                LOGGER.log(Level.SEVERE, "Risorsa non trovata: " + immagine);
                throw new FileNotFoundException("Risorsa non trovata: " + immagine);
            }

            this.image = new Image(inputStream);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problema nel caricamento dell'immagine", e);
        }
    }

    public ImageView getObstacleImage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(OBSTACLE_FXML_PATH));

            ImageView imageView = loader.load();
            imageView.setImage(this.image);
            return imageView;

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problema nel caricamento del file FXML ", e);
            return null;
        }
    }
    public String getNome() {
        return nome;
    }
    public String getCollisione() {
        return collisione;
    }
}
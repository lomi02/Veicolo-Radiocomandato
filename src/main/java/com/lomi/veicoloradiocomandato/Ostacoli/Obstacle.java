package com.lomi.veicoloradiocomandato.Ostacoli;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Obstacle implements ObstacleInterface {
    private static final Logger LOGGER = Logger.getLogger(Obstacle.class.getName());
    private static final String OBSTACLE_FXML_PATH = "/com/lomi/veicoloradiocomandato/obstacle.fxml";
    protected String nome;
    protected String collisione;
    protected Image immagine;

    public Obstacle(String nome, String immagine, String collisione) {
        this.nome = nome;
        this.collisione = collisione;

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

    public String getNome() {
        return nome;
    }

    public ImageView getObstacleImage() {
        ImageView imageView;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(OBSTACLE_FXML_PATH));
            imageView = loader.load();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problema nel caricamento del file FXML.", e);
            imageView = new ImageView();
        }

        if(this.immagine != null) {
            imageView.setImage(this.immagine);
        }

        return imageView;
    }

    public String getCollisione() {
        return collisione;
    }
}
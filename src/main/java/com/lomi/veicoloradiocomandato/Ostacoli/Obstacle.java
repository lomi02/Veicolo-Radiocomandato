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

    // Logger per raccogliere eventi/errori in questa classe
    private static final Logger LOGGER = Logger.getLogger(Obstacle.class.getName());

    // Percorso del file FXML dell'ostacolo
    private static final String OBSTACLE_FXML_PATH = "/com/lomi/veicoloradiocomandato/obstacle.fxml";

    private Image image;

    // Il nome dell'ostacolo
    protected String nome;

    // Parametro collisione
    protected String collisione;

    public Obstacle(String nome, String immagine, String collisione) {
        this.nome = nome;
        this.collisione = collisione;

        this.image = null;

        try {
            // Carica l'immagine
            InputStream inputStream = getClass().getResourceAsStream("/com/lomi/veicoloradiocomandato/img/" + immagine);

            // Se lo stream dell'immagine è null, lancia un'eccezione FileNotFoundException
            if (inputStream == null) {
                LOGGER.log(Level.SEVERE, "Risorsa non trovata: " + immagine);
                throw new FileNotFoundException("Risorsa non trovata: " + immagine);
            }

            // Create an Image object from the image stream and store it
            this.image = new Image(inputStream);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problema nel caricamento dell'immagine", e);
        }
    }

    // Metodo getter per fornire accesso esterno all'ImageView dell'ostacolo
    public ImageView getImmagine() {
        try {
            // Carica il file FXML per l'ostacolo
            FXMLLoader loader = new FXMLLoader(getClass().getResource(OBSTACLE_FXML_PATH));

            // Carica l'ImageView dal file FXML and set its image
            ImageView imageView = loader.load();
            imageView.setImage(this.image);
            return imageView;

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problema nel caricamento del file FXML ", e);
            // Return null or handle this as per your needs.
            return null;
        }
    }

    // Metodo getter per fornire accesso esterno al nome dell'ostacolo
    public String getNome() {
        return nome;
    }

    // Metodo getter per fornire accesso esterno al parametro collisione dell'ostacolo
    public String getCollisione() {
        return collisione;
    }

}
package com.lomi.veicoloradiocomandato.Vehicle;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class VeicoloData {
    private static final Logger LOGGER = Logger.getLogger(VeicoloData.class.getName());
    private final String codice;
    private final String marca;
    private final double frequenza;
    private final String colore;
    private Image image;
    private final String collisione;
    private static final String VEHICLE_FXML_PATH = "/com/lomi/veicoloradiocomandato/vehicle.fxml";

    public VeicoloData(String codice, String marca, double frequenza, String colore, String immagine, String collisione) {
        this.codice = codice;
        this.marca = marca;
        this.frequenza = frequenza;
        this.colore = colore;
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

    public String getCodice() {
        return codice;
    }

    public String getMarca() {
        return marca;
    }

    public double getFrequenza() {
        return frequenza;
    }

    public String getColore() {
        return colore;
    }

    public ImageView getVehicleImage() {
        try {
            // Carica il file FXML per l'ostacolo
            FXMLLoader loader = new FXMLLoader(getClass().getResource(VEHICLE_FXML_PATH));

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

    public String getCollisione() {
        return collisione;
    }
}
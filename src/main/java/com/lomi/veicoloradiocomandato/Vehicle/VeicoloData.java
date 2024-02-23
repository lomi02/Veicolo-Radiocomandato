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
    private final String marca;
    private Image image;
    private final String collisione;
    private static final String VEHICLE_FXML_PATH = "/com/lomi/veicoloradiocomandato/vehicle.fxml";

    public VeicoloData(String marca, String immagine, String collisione) {
        this.marca = marca;
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
    public String getMarca() {
        return marca;
    }
    public ImageView getVehicleImage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(VEHICLE_FXML_PATH));

            ImageView imageView = loader.load();
            imageView.setImage(this.image);
            return imageView;

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Problema nel caricamento del file FXML ", e);
            return null;
        }
    }

    public String getCollisione() {
        return collisione;
    }
}
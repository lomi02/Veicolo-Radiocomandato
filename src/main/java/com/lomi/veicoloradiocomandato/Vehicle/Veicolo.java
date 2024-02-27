package com.lomi.veicoloradiocomandato.Vehicle;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe astratta Veicolo rappresenta un veicolo generico con una marca e un'immagine associata.
 */
public abstract class Veicolo {

    private static final Logger LOGGER = Logger.getLogger(Veicolo.class.getName());
    private final String marca;
    private Image image;
    private static final String VEHICLE_FXML_PATH = "/com/lomi/veicoloradiocomandato/vehicle.fxml";

    /**
     * Costruttore della classe Veicolo.
     *
     * @param marca    La marca del veicolo.
     * @param immagine Il nome del file dell'immagine associata al veicolo.
     */
    public Veicolo(String marca, String immagine) {
        this.marca = marca;
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

    /**
     * Restituisce la marca del veicolo.
     *
     * @return La marca del veicolo.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Restituisce un'istanza di ImageView con l'immagine associata al veicolo.
     *
     * @return L'ImageView con l'immagine del veicolo.
     */
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
}